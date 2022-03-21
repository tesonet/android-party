package com.example.featureServer.presentation.utils

import com.example.domainServer.domain.model.Server

object TestData {
    private fun getSingleServer() = Server(
        name = "test server 1",
        distance = 131
    )

    fun getServerList() = listOf(
        getSingleServer(),
        getSingleServer().copy(name = "test server 2"),
        getSingleServer().copy(name = "test server 3")
    )
}
