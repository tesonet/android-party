package com.teso.net.data_flow.network

import com.teso.net.data_flow.network.api_models.ServerAnswer
import com.teso.net.data_flow.network.api_models.TokenAnswer
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @FormUrlEncoded
    @POST("tokens")
    fun getToken(
            @Field("username") username: String,
            @Field("password") password: String): Observable<TokenAnswer>

    @GET("servers")
    fun getSitesList(): Observable<List<ServerAnswer>>
}