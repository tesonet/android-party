package com.simplekjl.data.model

import com.google.gson.annotations.SerializedName

data class LoginRaw(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
