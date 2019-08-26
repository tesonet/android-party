package com.example.androidparty.networking

import android.util.Log
import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.UserRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class AuthClientImpl : AuthClient {


    private val baseURL = "http://playground.tesonet.lt/v1/"

    private var retrofit: Retrofit
    private var clientCalls: ClientCalls

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        clientCalls = retrofit.create(ClientCalls::class.java)
    }

    override fun sendCredentials(userRequest: UserRequest, listener: ResponseListener<AuthToken>) {
        val call = clientCalls.getAuthToken(userRequest)
        call.enqueue(object : Callback<AuthToken> {
            override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                listener.onResult(response.body())
            }

            override fun onFailure(call: Call<AuthToken>, t: Throwable) {
                Log.e("AuthClientImpl", t.message!!)
            }

        })
    }

}

