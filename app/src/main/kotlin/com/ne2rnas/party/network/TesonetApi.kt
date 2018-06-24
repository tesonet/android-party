package com.ne2rnas.party.network

import com.ne2rnas.party.data.login.LoginResponse
import com.ne2rnas.party.data.servers.Server
import io.reactivex.Observable
import retrofit2.http.*

interface TesonetApi {
    @FormUrlEncoded
    @POST("/v1/tokens")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<LoginResponse>

    @GET("/v1/servers")
    fun getServers(@Header("authorization") token: String?): Observable<List<Server>>
}
