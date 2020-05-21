package com.baruckis.androidparty.remote.api

import com.baruckis.androidparty.remote.model.RequestUser
import com.baruckis.androidparty.remote.model.ResponseToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TesonetApiService {

    @Headers("Content-Type: application/json")
    @POST
    fun sendAuthorization(@Body body: RequestUser): Single<ResponseToken>

}