package com.example.mutiaralaundry.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Boolean
)
