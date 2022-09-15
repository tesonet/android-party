package com.ac.androidparty.login.data.remote

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @POST("tokens")
    @Headers(CONTENT_TYPE_HEADERS)
    suspend fun login(@Body login: LoginRequest): Token

    companion object {
        private const val CONTENT_TYPE_HEADERS = "Content-Type: application/json"
        const val BASE_URL = " https://playground.tesonet.lt/v1/"
    }
}