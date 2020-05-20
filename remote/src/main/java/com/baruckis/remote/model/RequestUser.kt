package com.baruckis.remote.model

import com.google.gson.annotations.SerializedName

data class RequestUser(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
) {
}