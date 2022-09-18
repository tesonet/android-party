package com.ac.androidparty.login.data.remote

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface LoginApi {

    @POST(TOKENS)
    @Headers(CONTENT_TYPE_HEADERS)
    suspend fun login(@Body login: LoginRequest): Token

    private companion object {
        const val TOKENS = "tokens"
        const val CONTENT_TYPE_HEADERS = "Content-Type: application/json"
    }
}