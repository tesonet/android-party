package com.ac.androidparty.servers.data.remote

import com.ac.androidparty.servers.data.localsource.ServerEntity
import com.ac.androidparty.servers.domain.model.Server
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse(
    @SerialName("name") val name: String,
    @SerialName("distance") val distance: Int
)

fun ServerResponse.toServer() = Server(name = name, distance = distance)

fun ServerResponse.toServerEntity() = ServerEntity(
    serverId = name.split(" ").last().removePrefix("#").toInt(),
    serverName = name,
    serverDistance = distance
)