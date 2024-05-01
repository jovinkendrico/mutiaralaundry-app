package com.example.mutiaralaundry.data.response

import com.google.gson.annotations.SerializedName

data class PaketResponse(

	@field:SerializedName("paket")
	val paket: List<PaketItem>
)

data class PaketItem(

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
