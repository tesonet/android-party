package com.teso.net.data_flow.network

import com.teso.net.data_flow.network.api_models.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("token")
    fun getToken(
            @Field("username") userName: String,
            @Field("password") password: String): Observable<TokenAnswer>

    @GET("servers")
    fun getSitesList(): Flowable<List<ServerAnswer>>
}