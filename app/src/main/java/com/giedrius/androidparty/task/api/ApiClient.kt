package com.giedrius.androidparty.task.api

import com.giedrius.androidparty.task.viewmodel.Token
import com.giedrius.androidparty.task.viewmodel.Server
import com.giedrius.androidparty.task.viewmodel.LoginBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {
    @POST("tokens")
    fun getToken(@Body body: LoginBody): Call<Token>

    @GET("servers")
    fun getServers(): Call<List<Server>>
}