package com.example.androidparty

import retrofit2.Call
import retrofit2.http.POST

interface Api {

    @POST("/v1/tokens")
    fun authenticate(userData: UserData): Call<TokenObject>
}