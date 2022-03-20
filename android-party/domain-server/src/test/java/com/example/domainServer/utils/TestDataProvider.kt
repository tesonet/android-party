package com.example.domainServer.utils

import com.example.domainServer.data.db.entity.ServerEntity
import com.example.domainServer.data.dto.ServerDto
import com.example.domainServer.domain.model.Server

object TestDataProvider {
    fun getSingleServerEntity() = ServerEntity(
        name = "test server 1",
        distance = 131
    )

    fun getServerEntityList() = listOf(
        getSingleServerEntity(),
        getSingleServerEntity().copy(name = "test server 2"),
        getSingleServerEntity().copy(name = "test server 3")
    )

    fun getSingleServerDto() = ServerDto(
        name = "test server 1",
        distance = 131
    )

    fun getServerDtoList() = listOf(
        getSingleServerDto(),
        getSingleServerDto().copy(name = "test server 2"),
        getSingleServerDto().copy(name = "test server 3")
    )

    fun getSingleServer() = Server(
        name = "test server 1",
        distance = 131
    )

    fun getServerList() = listOf(
        getSingleServer(),
        getSingleServer().copy(name = "test server 2"),
        getSingleServer().copy(name = "test server 3")
    )
}