package com.example.mutiaralaundry.data.response

import com.google.gson.annotations.SerializedName

data class CabangResponse(

	@field:SerializedName("cabang")
	val cabang: List<CabangItem>
)

data class CabangItem(

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
