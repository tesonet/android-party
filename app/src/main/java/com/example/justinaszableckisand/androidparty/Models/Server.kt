package com.example.justinaszableckisand.androidparty.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Server {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("distance")
    @Expose
    var distance: Int? = null
}
