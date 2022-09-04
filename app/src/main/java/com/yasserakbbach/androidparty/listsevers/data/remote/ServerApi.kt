package com.yasserakbbach.androidparty.listsevers.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers

interface ServerApi {

    @Headers("Content-Type: application/json")
    @GET("servers")
    suspend fun getServers(): List<ServerResponse>
}