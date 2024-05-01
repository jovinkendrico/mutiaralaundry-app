package com.example.mutiaralaundry.data.response

import com.google.gson.annotations.SerializedName

data class PesananResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class Paket(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("harga")
	val harga: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)

data class Cabang(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("alamat")
	val alamat: String
)

data class DataItem(

	@field:SerializedName("cabang")
	val cabang: Cabang,

	@field:SerializedName("cabang_id")
	val cabangId: Int,

	@field:SerializedName("biaya")
	val biaya: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("qty")
	val qty: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("customer_id")
	val customerId: Int,

	@field:SerializedName("paket_id")
	val paketId: Int,

	@field:SerializedName("paket")
	val paket: Paket
)
