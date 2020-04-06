package com.example.androidparty.utils

import com.google.gson.annotations.SerializedName

data class TokenObject(@SerializedName("token") val token: String, @SerializedName("message") val message: String?)