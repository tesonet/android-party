package com.yasserakbbach.androidparty.listsevers.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse(
    @SerialName("name") val name: String,
    @SerialName("distance") val distance: Int,
)
