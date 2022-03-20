package com.example.domain_server.data.service

import com.example.domain_server.data.dto.ServerDto
import retrofit2.http.GET

interface ServerListService {
    @GET("servers")
    suspend fun fetchServerList(): List<ServerDto>
}