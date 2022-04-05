package com.thescriptan.tesonetparty.network

import com.thescriptan.tesonetparty.login.model.LoginRequest
import com.thescriptan.tesonetparty.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("tokens")
    suspend fun login(@Body body: LoginRequest): Response<LoginResponse>
}
