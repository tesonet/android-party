package com.simplekjl.data.model

import com.google.gson.annotations.SerializedName

data class ServerDetailsRaw(
    @SerializedName("name")
    val name: String,
    @SerializedName("distance")
    val distance: Int,
)
