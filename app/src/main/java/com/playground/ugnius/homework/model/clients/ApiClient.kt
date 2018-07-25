package com.playground.ugnius.homework.model.clients

import com.playground.ugnius.homework.model.entities.AccessToken
import com.playground.ugnius.homework.model.entities.Server
import com.playground.ugnius.homework.model.entities.UserRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiClient {

    @POST("v1/tokens")
    fun requestToken(@Body userRequest: UserRequest): Single<AccessToken>

    @GET("v1/servers")
    fun getServers(@Header("Authorization") accessToken: String): Single<List<Server>>
}