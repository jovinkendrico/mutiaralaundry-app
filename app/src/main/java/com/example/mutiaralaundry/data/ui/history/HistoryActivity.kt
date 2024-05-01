package com.example.mutiaralaundry.data.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mutiaralaundry.R
import com.example.mutiaralaundry.data.ui.ViewModelFactory
import com.example.mutiaralaundry.data.ui.add.AddActivity
import com.example.mutiaralaundry.data.ui.add.AddViewModel
import com.example.mutiaralaundry.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel by viewModels<HistoryViewModel>{
        ViewModelFactory.getInstance(this)
    }
    private val adapter = HistoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val customerId = intent.getIntExtra("id",0)

        historyViewModel.pesanan(customerId)

        binding.fab.setOnClickListener{
            val intent = Intent(this,AddActivity::class.java)
            intent.putExtra("id",customerId)
            startActivity(intent)
        }

        binding.detailRv.layoutManager = LinearLayoutManager(this)
        binding.detailRv.setHasFixedSize(true)

        historyViewModel.getListPesanan().observe(this){
            adapter.setData(it)
        }
        binding.detailRv.adapter = adapter
    }
}