package com.example.justinaszableckisand.androidparty

import com.example.justinaszableckisand.androidparty.Models.Server
import com.example.justinaszableckisand.androidparty.Models.Token
import com.example.justinaszableckisand.androidparty.Models.UserLogin
import retrofit2.Call
import retrofit2.http.*

interface ApiCalls {

    @POST(Constants.TOKENS)
    fun getToken(@Body userLogin: UserLogin) : Call<Token>

    @GET(Constants.SERVERS)
    fun getServers(@Header(Constants.AUTHORIZATION) token : String) : Call<List<Server>>

}