package com.jonastiskus.testio.model

import com.google.gson.annotations.SerializedName

data class ServerGsonModel(

    @SerializedName("name")
    val name : String,

    @SerializedName("distance")
    val distance : Int?
)