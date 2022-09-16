package com.ac.androidparty.servers.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Server(
    @SerialName("name") val name: String,
    @SerialName("distance") val distance: String
)