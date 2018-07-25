package com.teso.net.data_flow.network

import com.teso.net.data_flow.network.api_models.ServerAnswer
import com.teso.net.data_flow.network.api_models.TokenAnswer
import io.reactivex.Observable
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("tokens")
    fun getToken(
            @Field("username") username: String,
            @Field("password") password: String): Observable<TokenAnswer>

    @GET("servers")
    fun getSitesList(): Observable<List<ServerAnswer>>
}