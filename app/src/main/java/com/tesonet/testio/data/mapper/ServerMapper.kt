package com.tesonet.testio.data.mapper

import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.remote.entity.ApiServer


object ServerMapper {

    public fun map(apiServer: ApiServer, index: Int) = Server(index, apiServer.name, apiServer.distance)

    public fun map(server: Server) = ApiServer(server.name, server.distance)
}