package com.jonastiskus.testio.network.service

import com.jonastiskus.testio.model.AuthGsonModel
import com.jonastiskus.testio.model.ServerGsonModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("tokens")
    @Headers("Content-type: application/json")
    fun getToken(@Query("username")userName : String,
                 @Query("password") password : String) : Call<AuthGsonModel>

    @GET("servers")
    @Headers("Content-type: application/json")
    fun getServers(@Header("Authorization") authToken : String) : Call<List<ServerGsonModel>>

}