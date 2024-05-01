package com.example.mutiaralaundry.data.ui.login

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
import com.example.mutiaralaundry.databinding.ActivityLoginBinding
import com.google.gson.JsonObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSubmitValidation.setOnClickListener{
            if(validateInputs()){
                val username = binding.etLogin.text.toString()
                val password = binding.etPassword.text.toString()
                val param = JsonObject().apply {
                    addProperty("username", username)
                    addProperty("password", password)
                }
                loginViewModel.login(param)
                loginViewModel.getLoginResponse().observe(this){
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
        return isFieldValid(binding.etLogin, "username") &&
                isFieldValid(binding.etPassword, "password")
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