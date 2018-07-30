package com.alex.tesonettesttask.logic.network

import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.network.request.LoginRequest
import com.alex.tesonettesttask.logic.network.response.AccessTokenResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TesonetService {

    @POST("v1/tokens")
    fun requestToken(@Body loginRequest: LoginRequest): Observable<Response<AccessTokenResponse>>

    @GET("v1/servers")
    fun getServers(): Observable<Response<List<Server>>>
}