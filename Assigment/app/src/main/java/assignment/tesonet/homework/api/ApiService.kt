package assignment.tesonet.homework.api

import assignment.tesonet.homework.api.response.LoginResponse
import assignment.tesonet.homework.api.response.Server
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("/v1/tokens")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Call<LoginResponse>

    @GET("/v1/servers")
    fun getServers(@Header("authorization") token: String?): Call<List<Server>>
}