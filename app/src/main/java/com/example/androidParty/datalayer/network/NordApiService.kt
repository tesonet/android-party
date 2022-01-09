package com.example.androidParty.datalayer.network

import com.example.androidParty.datalayer.dto.ServerDto
import com.example.androidParty.datalayer.dto.UserDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface NordApiService {
    @Headers("Content-Type: application/json")
    @POST("tokens")
    suspend fun login(@Body body: RequestBody): Response<UserDto>

    @GET("servers")
    suspend fun getDataServers(@Header("Authorization") token: String): Response<List<ServerDto>>
}