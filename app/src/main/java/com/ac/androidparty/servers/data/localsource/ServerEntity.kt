package com.ac.androidparty.servers.data.localsource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ac.androidparty.servers.data.remote.ServerResponse

@Entity
data class ServerEntity(
    @PrimaryKey val serverId: Int,
    @ColumnInfo(name = "server_name") val serverName: String,
    @ColumnInfo(name = "server_distance") val serverDistance: Int
)

fun ServerEntity.toServerResponse() = ServerResponse(
    name = serverName,
    distance = serverDistance
)