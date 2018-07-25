package com.playground.ugnius.homework.model.clients

import com.playground.ugnius.homework.model.entities.AccessToken
import com.playground.ugnius.homework.model.entities.UserRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST("v1/tokens")
    fun requestToken(@Body userRequest: UserRequest): Single<AccessToken>
}