package com.example.domainServer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerDto(
    @SerialName("name") val name: String,
    @SerialName("distance") val distance: Int,
)
