package com.simplekjl.data.client

import com.simplekjl.data.model.LoginRaw
import com.simplekjl.data.model.ServerDetailsRaw
import com.simplekjl.data.model.TokenRaw
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClientService {
    @POST("tokens")
    @Headers("No-Authentication: true")
    suspend fun login(@Body credentials: LoginRaw): Response<TokenRaw>

    @GET("servers")
    suspend fun getServers(): Response<ArrayList<ServerDetailsRaw>>
}
