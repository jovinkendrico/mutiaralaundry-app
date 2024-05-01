package com.example.mutiaralaundry.data.response

import com.google.gson.annotations.SerializedName

data class TransaksiResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Boolean
)
