package com.assignment.android_party2.api

import com.assignment.android_party2.servers.models.ServerModel
import com.assignment.android_party2.login.models.TokenModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("tokens")
    fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<TokenModel>

    @GET("servers")
    fun getServers(
        @Header("Authorization") token: String
    ): Call<List<ServerModel>>

    companion object {
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl("http://playground.tesonet.lt/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}