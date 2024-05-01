package com.example.mutiaralaundry.data.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mutiaralaundry.data.repository.UserRepository
import com.example.mutiaralaundry.data.response.LoginResponse
import com.google.gson.JsonObject

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    private lateinit var loginResponse: LiveData<LoginResponse>
    fun login(jsonObject: JsonObject){
        loginResponse = repository.login(jsonObject)
    }
    fun getLoginResponse(): LiveData<LoginResponse> {
        return loginResponse
    }
}