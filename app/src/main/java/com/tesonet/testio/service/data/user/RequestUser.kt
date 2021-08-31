package com.tesonet.testio.service.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestUser(

    @SerializedName("username")
    @Expose
    val username: String? = null,

    @SerializedName("password")
    @Expose
    val password: String? = null

)
