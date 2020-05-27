package com.baruckis.androidparty.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseServer(
    @SerializedName("name") val name: String,
    @SerializedName("distance") val distance: Int
)