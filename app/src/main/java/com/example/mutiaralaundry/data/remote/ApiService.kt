package com.example.mutiaralaundry.data.remote

import com.example.mutiaralaundry.data.response.CabangResponse
import com.example.mutiaralaundry.data.response.LoginResponse
import com.example.mutiaralaundry.data.response.PaketResponse
import com.example.mutiaralaundry.data.response.PesananResponse
import com.example.mutiaralaundry.data.response.RegisterResponse
import com.example.mutiaralaundry.data.response.TransaksiResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("paket")
    fun getPaket() : Call<PaketResponse>

    @GET("cabang")
    fun getCabang() : Call<CabangResponse>

    @GET("pesanan/{id}")
    fun getPesanan(@Path("id")id:Int): Call<PesananResponse>

    @POST("login")
    fun login(@Body raw: JsonObject) : Call<LoginResponse>

    @POST("register")
    fun register(@Body raw: JsonObject) : Call<RegisterResponse>

    @POST("transaksi")
    fun transaksi(@Body raw: JsonObject) : Call<TransaksiResponse>

}