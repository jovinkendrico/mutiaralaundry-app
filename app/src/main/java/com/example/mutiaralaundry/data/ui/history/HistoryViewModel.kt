package com.example.mutiaralaundry.data.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mutiaralaundry.data.repository.UserRepository
import com.example.mutiaralaundry.data.response.CabangItem
import com.example.mutiaralaundry.data.response.DataItem
import com.google.gson.JsonObject

class HistoryViewModel(private val repository: UserRepository): ViewModel() {

    private lateinit var listPesananItem: LiveData<List<DataItem>>

    fun pesanan(id: Int) {
        listPesananItem = repository.pesanan(id)
    }
    fun getListPesanan(): LiveData<List<DataItem>>{
        return listPesananItem
    }
}