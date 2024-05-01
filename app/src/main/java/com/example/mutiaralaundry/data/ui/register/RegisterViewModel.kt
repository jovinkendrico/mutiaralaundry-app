package com.example.mutiaralaundry.data.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mutiaralaundry.data.repository.UserRepository
import com.example.mutiaralaundry.data.response.LoginResponse
import com.example.mutiaralaundry.data.response.RegisterResponse
import com.google.gson.JsonObject

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    private lateinit var registerResponse: LiveData<RegisterResponse>
    fun register(jsonObject: JsonObject){
        registerResponse = repository.register(jsonObject)
    }
    fun getRegisterResponse(): LiveData<RegisterResponse> {
        return registerResponse
    }
}