package com.yasserakbbach.androidparty.login.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/tokens")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse
}