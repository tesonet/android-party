package com.example.androidParty.datalayer.network

import com.example.androidParty.datalayer.LoginCredentials
import com.example.androidParty.datalayer.dto.ServerDto
import com.example.androidParty.datalayer.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface NordApiService {
  @Headers("Content-Type: application/json")
  @POST("tokens")
  suspend fun login(@Body body: LoginCredentials): Response<UserDto>

  @GET("servers")
  suspend fun getDataServers(@Header("Authorization") token: String): Response<List<ServerDto>>
}