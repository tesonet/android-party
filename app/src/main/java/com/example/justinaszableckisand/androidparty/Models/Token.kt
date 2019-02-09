package com.example.justinaszableckisand.androidparty.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Token {
    @SerializedName("token")
    @Expose
    var token: String? = null
}
