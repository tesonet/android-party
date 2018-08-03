package com.k4dima.androidparty.features.app.data.api

import com.k4dima.androidparty.features.login.data.model.Token
import com.k4dima.androidparty.features.main.data.model.Server
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface TesonetService {
    @GET("/v1/servers")
    fun servers(@Header("Authorization") bearer: String): Single<List<Server>>

    @Multipart
    @POST("/v1/tokens")
    fun token(@PartMap authorization: Map<String, @JvmSuppressWildcards RequestBody>): Single<Token>
}