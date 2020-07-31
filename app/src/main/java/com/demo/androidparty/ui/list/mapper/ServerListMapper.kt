package com.demo.androidparty.ui.list.mapper

import com.demo.androidparty.dto.Server
import com.demo.androidparty.dto.ServerModel

interface ServerListMapper {
    fun map(server: ServerModel) : Server
}