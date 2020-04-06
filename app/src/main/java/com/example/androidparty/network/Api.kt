package com.example.androidparty.network

import com.example.androidparty.db.ServerEntity
import com.example.androidparty.utils.TokenObject
import com.example.androidparty.utils.UserData
import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @POST("/v1/tokens")
    @Headers("Content-type: application/json")
    fun authenticate(@Body userData: UserData): Observable<TokenObject>

    @GET("/v1/servers")
    @Headers("Content-type: application/json")
    fun getServers(@Header("Authorization") token: String): Observable<List<ServerEntity>>
}