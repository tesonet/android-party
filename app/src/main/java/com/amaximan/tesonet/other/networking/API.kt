package com.amaximan.tesonet.other.networking

import com.amaximan.tesonet.model.database.server.Server
import com.amaximan.tesonet.model.networking.TokenRequest
import com.amaximan.tesonet.model.networking.TokenResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    companion object {
        val BASE_URL: String
            get() = "http://playground.tesonet.lt/v1/"
    }


    /**
     * Registration/login
     */
    @POST("tokens")
    fun getToken(@Body data: TokenRequest): Single<Response<TokenResponse>>

    @GET("servers")
    fun getServers(@Header("Authorization") token: String): Single<Response<List<Server>>>
}