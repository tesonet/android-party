package com.giedrius.androidparty.task.server

import com.giedrius.androidparty.task.model.Token
import com.giedrius.androidparty.task.model.Server
import com.giedrius.androidparty.task.model.LoginBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("tokens")
    fun getToken(@Body body: LoginBody): Call<Token>

    @GET("servers")
    fun getServers(): Call<List<Server>>
}