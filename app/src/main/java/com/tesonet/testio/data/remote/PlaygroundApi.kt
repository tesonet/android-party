package com.tesonet.testio.data.remote

import com.tesonet.testio.data.remote.entity.ApiCredentials
import com.tesonet.testio.data.remote.entity.ApiServer
import com.tesonet.testio.data.remote.entity.ApiToken
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PlaygroundApi {
    companion object {
        const val BASE_URL = "http://playground.tesonet.lt/v1/"
    }

    @POST("tokens")
    fun login(@Body credentials: ApiCredentials): Deferred<ApiToken>

    @GET("servers")
    fun getServers(@Header("Authorization") token: ApiToken): Deferred<List<ApiServer>>
}