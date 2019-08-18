package com.example.androidparty.networking

import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.Server
import com.example.androidparty.model.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientCalls {

    @POST("tokens")
    fun getAuthToken(@Body ur : UserRequest): Call<AuthToken>

    @GET("servers")
    fun getServers(): Call<List<Server>>
}