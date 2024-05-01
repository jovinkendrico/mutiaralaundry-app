package com.example.mutiaralaundry.data.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mutiaralaundry.R
import com.example.mutiaralaundry.data.ui.ViewModelFactory
import com.example.mutiaralaundry.data.ui.history.HistoryActivity
import com.example.mutiaralaundry.databinding.ActivityAddBinding
import com.google.gson.JsonObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val addViewModel by viewModels<AddViewModel>{
        ViewModelFactory.getInstance(this)
    }
    private var selectedCabangId: Int = -1
    private var selectedPaketId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val customerId = intent.getIntExtra("id",0)
        addViewModel.getCabang()
        addViewModel.getPaket()
        addViewModel.getListCabang().observe(this) { cabangList ->
            val cabangNames = cabangList.map { it.nama }
            // Populate the cabang spinner
            val cabangAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cabangNames)
            cabangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.tvCabang.setAdapter(cabangAdapter)
        }

        // Observe the paket data
        addViewModel.getListPaket().observe(this) { paketList ->
            val paketNames = paketList.map { it.nama + "-" + it.harga }

            // Populate the paket spinner
            val paketAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paketNames )
            paketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.tvPaket.setAdapter(paketAdapter)
        }
        val calendar = Calendar.getInstance()
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        binding.btnSubmit.setOnClickListener{
            addViewModel.getListCabang().observe(this){cabangList->
                selectedCabangId = cabangList.find{ it.nama == binding.tvCabang.text.toString()}?.id!!
            }
            addViewModel.getListPaket().observe(this){paketList->
                selectedPaketId = paketList.find{ it.nama + "-" + it.harga == binding.tvPaket.text.toString()}?.id!!
            }
            val param = JsonObject().apply {
                addProperty("tanggal", currentDate )
                addProperty("customer_id", customerId)
                addProperty("paket_id", selectedPaketId)
                addProperty("cabang_id", selectedCabangId)
                addProperty("qty", binding.etQty.text.toString())
                addProperty("biaya", binding.etBiaya.text.toString())
            }
            addViewModel.transaksi(param)
            addViewModel.getTransaksiResponse().observe(this){
                if(!it.error){
                    val intent = Intent(this,HistoryActivity::class.java)
                    intent.putExtra("id",customerId)
                    startActivity(intent)
                }
            }

        }
    }
}