package com.demo.androidparty.ui.list.mapper

import com.demo.androidparty.dto.Server
import com.demo.androidparty.dto.ServerModel
import javax.inject.Inject

class ServerListMapperImpl @Inject constructor() : ServerListMapper {
    override fun map(server: ServerModel): Server = Server(server.name, server.distance)
}