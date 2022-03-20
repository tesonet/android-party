package com.example.domainServer.data.service

import com.example.domainServer.data.dto.ServerDto
import retrofit2.http.GET

interface ServerListService {
    @GET("servers")
    suspend fun fetchServerList(): List<ServerDto>
}
