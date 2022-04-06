package com.thescriptan.tesonetparty.network

import com.thescriptan.tesonetparty.list.model.ServerInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ListApi {

    @GET("servers")
    suspend fun getServers(@Header("Authorization") token: String): Response<List<ServerInfo>>
}
