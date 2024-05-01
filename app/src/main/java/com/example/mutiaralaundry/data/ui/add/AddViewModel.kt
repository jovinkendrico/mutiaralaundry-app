package com.example.mutiaralaundry.data.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mutiaralaundry.data.repository.UserRepository
import com.example.mutiaralaundry.data.response.CabangItem
import com.example.mutiaralaundry.data.response.LoginResponse
import com.example.mutiaralaundry.data.response.PaketItem
import com.example.mutiaralaundry.data.response.TransaksiResponse
import com.google.gson.JsonObject

class AddViewModel(private val repository: UserRepository): ViewModel() {

    private lateinit var listCabangItem : LiveData<List<CabangItem>>
    private lateinit var listPaketItem : LiveData<List<PaketItem>>
    private lateinit var transaksiResponse: LiveData<TransaksiResponse>

    fun getCabang(){
        listCabangItem = repository.getCabang()
    }
    fun getPaket(){
        listPaketItem = repository.getPaket()
    }
    fun transaksi(jsonObject: JsonObject){
        transaksiResponse = repository.transaksi(jsonObject)
    }
    fun getTransaksiResponse(): LiveData<TransaksiResponse> {
        return transaksiResponse
    }

    fun getListPaket(): LiveData<List<PaketItem>>{
        return listPaketItem
    }

    fun getListCabang(): LiveData<List<CabangItem>>{
        return listCabangItem
    }
}