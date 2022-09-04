package com.yasserakbbach.androidparty.listsevers.data.mapper

import com.yasserakbbach.androidparty.listsevers.data.local.ServerEntity
import com.yasserakbbach.androidparty.listsevers.data.remote.ServerResponse
import com.yasserakbbach.androidparty.listsevers.domain.model.Server

fun Server.toServerEntity(): ServerEntity =
    ServerEntity(
        name = name,
        distance = distance,
    )

fun ServerEntity.toServer(): Server =
    Server(
        name = name,
        distance = distance,
    )

fun ServerResponse.toServerEntity(): ServerEntity =
    ServerEntity(
        name = name,
        distance = distance,
    )

fun ServerResponse.toServer(): Server =
    Server(
        name = name,
        distance = distance,
    )

fun List<ServerResponse>.toServerEntityList(): List<ServerEntity> =
    map { it.toServerEntity() }