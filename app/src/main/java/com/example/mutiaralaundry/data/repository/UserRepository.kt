package com.example.mutiaralaundry.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mutiaralaundry.data.remote.ApiService
import com.example.mutiaralaundry.data.response.CabangItem
import com.example.mutiaralaundry.data.response.CabangResponse
import com.example.mutiaralaundry.data.response.DataItem
import com.example.mutiaralaundry.data.response.LoginResponse
import com.example.mutiaralaundry.data.response.PaketItem
import com.example.mutiaralaundry.data.response.PaketResponse
import com.example.mutiaralaundry.data.response.PesananResponse
import com.example.mutiaralaundry.data.response.RegisterResponse
import com.example.mutiaralaundry.data.response.TransaksiResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {

    fun pesanan(id: Int): LiveData<List<DataItem>>{
        val pesananLiveData = MutableLiveData<List<DataItem>>()
        apiService.getPesanan(id).enqueue(object : Callback<PesananResponse>{
            override fun onResponse(call: Call<PesananResponse>, response: Response<PesananResponse>) {
                val data = response.body()
                if (data != null) {
                    pesananLiveData.value = data.data
                }
            }

            override fun onFailure(call: Call<PesananResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return pesananLiveData
    }

    fun register(jsonObject: JsonObject): LiveData<RegisterResponse>{
        val registerLiveData = MutableLiveData<RegisterResponse>()
        apiService.register(jsonObject).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                val data = response.body()
                registerLiveData.value = data
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }

        })
        return registerLiveData

    }
    fun login(jsonObject: JsonObject): LiveData<LoginResponse>{
        val loginLiveData = MutableLiveData<LoginResponse>()
        apiService.login(jsonObject).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val data = response.body()
                loginLiveData.value = data
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }

        })

        return loginLiveData
    }
    fun transaksi(jsonObject: JsonObject): LiveData<TransaksiResponse>{
        val transaksiLiveData = MutableLiveData<TransaksiResponse>()
        apiService.transaksi(jsonObject).enqueue(object : Callback<TransaksiResponse> {
            override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
                val data = response.body()
                transaksiLiveData.value = data
            }

            override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }

        })

        return transaksiLiveData
    }
    
    fun getCabang(): LiveData<List<CabangItem>>
    {
        val cabangLiveData = MutableLiveData<List<CabangItem>>()
        apiService.getCabang().enqueue(object : Callback<CabangResponse> {
            override fun onResponse(call: Call<CabangResponse>, response: Response<CabangResponse>) {
                val data = response.body()
                if (data != null) {
                    cabangLiveData.value = data.cabang
                }
            }

            override fun onFailure(call: Call<CabangResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }

        })
        return cabangLiveData
    }
    fun getPaket(): LiveData<List<PaketItem>>
    {
        val paketLiveData = MutableLiveData<List<PaketItem>>()
        apiService.getPaket().enqueue(object : Callback<PaketResponse> {
            override fun onResponse(call: Call<PaketResponse>, response: Response<PaketResponse>) {
                val data = response.body()
                if (data != null) {
                    paketLiveData.value = data.paket
                }
            }

            override fun onFailure(call: Call<PaketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }

        })
        return paketLiveData
    }
    companion object {
        const val TAG="UserRepository"
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository=
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}