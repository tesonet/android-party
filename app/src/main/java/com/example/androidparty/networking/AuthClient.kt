package com.example.androidparty.networking

import android.util.Log
import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.UserRequest
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class AuthClient : AuthClientFace {


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

    override fun sendCredentials(username: String, password: String, listener: ResponseListener<AuthToken>) {
        val call = clientCalls.getAuthToken(UserRequest(username, password))
        call.enqueue(object : Callback<AuthToken> {
            override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                Log.e("Dtag", response.body().toString())
                listener.onResult(response.body())
            }

            override fun onFailure(call: Call<AuthToken>, t: Throwable) {
                Log.e("AuthClient", t.message!!)
            }

        })
    }

}

