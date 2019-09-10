package com.jonastiskus.testio.model

import com.google.gson.annotations.SerializedName

data class AuthGsonModel(
    @SerializedName("token") val token : String,
    @SerializedName("message") val message : String
)