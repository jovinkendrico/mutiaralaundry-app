package com.example.mutiaralaundry.di

import android.content.Context
import com.example.mutiaralaundry.data.remote.ApiConfig
import com.example.mutiaralaundry.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}