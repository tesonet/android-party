package com.demo.androidparty.network

import com.demo.androidparty.dto.LoginData
import com.demo.androidparty.dto.TokenData
import com.demo.androidparty.dto.ServerModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("tokens")
    suspend fun login(@Body loginData: LoginData): Response<TokenData>

    @GET("servers")
    suspend fun fetchServers(): Response<List<ServerModel>>
}