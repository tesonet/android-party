package com.k4dima.party.app.data.api

import com.k4dima.party.login.data.model.Token
import com.k4dima.party.main.data.model.Server
import okhttp3.RequestBody
import retrofit2.http.*

interface TesonetService {
    @GET("/v1/servers")
    suspend fun servers(@Header("Authorization") bearer: String): List<Server>

    @Multipart
    @POST("/v1/tokens")
    suspend fun token(@PartMap authorization: Map<String, @JvmSuppressWildcards RequestBody>): Token
}