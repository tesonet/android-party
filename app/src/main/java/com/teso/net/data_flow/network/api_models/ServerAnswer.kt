package com.teso.net.data_flow.network.api_models

import com.google.gson.annotations.SerializedName

data class ServerAnswer(
        @field:SerializedName("name")
        val name: String?,

        @field:SerializedName("distance")
        val distance: Int?
)