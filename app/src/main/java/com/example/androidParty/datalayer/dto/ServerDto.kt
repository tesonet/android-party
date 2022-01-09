package com.example.androidParty.datalayer.dto

import com.example.androidParty.presentation.serverList.domain.entity.Server

data class ServerDto(val name: String, val distance: String)

fun ServerDto.toServer(): Server {
    return Server(name = name, distance = distance)
}