package com.baruckis.androidparty.remote.api

import com.baruckis.androidparty.remote.model.RequestUser
import com.baruckis.androidparty.remote.model.ResponseServer
import com.baruckis.androidparty.remote.model.ResponseToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TesonetApiService {

    @POST("tokens")
    fun sendAuthorization(@Body body: RequestUser): Single<ResponseToken>

    @GET("servers")
    fun getServers(): Single<List<ResponseServer>>

}