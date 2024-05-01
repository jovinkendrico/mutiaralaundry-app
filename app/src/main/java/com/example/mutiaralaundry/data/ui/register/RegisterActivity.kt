package com.example.mutiaralaundry.data.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mutiaralaundry.R
import com.example.mutiaralaundry.data.ui.ViewModelFactory
import com.example.mutiaralaundry.data.ui.history.HistoryActivity
import com.example.mutiaralaundry.databinding.ActivityRegisterBinding
import com.google.gson.JsonObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSubmit.setOnClickListener{
            if(validateInputs()){
                val nama = binding.etNama.text.toString()
                val username = binding.etUsername.text.toString()
                val alamat = binding.etAlamat.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val param = JsonObject().apply {
                    addProperty("nama", nama)
                    addProperty("username", username)
                    addProperty("alamat", alamat)
                    addProperty("email", email)
                    addProperty("password", password)
                }
                registerViewModel.register(param)
                registerViewModel.getRegisterResponse().observe(this){
                    if(!it.error){
                        val intent = Intent(this,HistoryActivity::class.java)
                        intent.putExtra("id",it.id)
                        startActivity(intent)
                    }
                }
            }
        }
    }
    private fun validateInputs(): Boolean {
        // Perform validation for each input field
        return isFieldValid(binding.etAlamat, "alamat") &&
                isFieldValid(binding.etPassword, "password") &&
                isFieldValid(binding.etNama, "nama") &&
                isFieldValid(binding.etEmail, "email") &&

                isFieldValid(binding.etUsername, "username")

    }
    private fun isFieldValid(editText: EditText, fieldName: String): Boolean {
        val input = editText.text.toString().trim()
        if (input.isEmpty()) {
            editText.error = "$fieldName cannot be empty"
            return false
        }
        // Add more validation rules as needed
        // Return true if the input is valid, otherwise false
        return true
    }
}