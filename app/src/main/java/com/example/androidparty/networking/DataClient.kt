package com.example.androidparty.networking

import android.util.Log
import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.Server
import com.example.androidparty.model.UserRequest
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataClient(private val token: String): DataClientFace {

    private val baseURL = "http://playground.tesonet.lt/v1/"

    private var okClient: OkHttpClient
    private var retrofit: Retrofit
    private var clientCalls: ClientCalls

    init {
        okClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val baseRequest: Request = chain.request()
            val requestBuilder = baseRequest.newBuilder().header("Authorization", "Bearer $token").method(baseRequest.method(), baseRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        clientCalls = retrofit.create(ClientCalls::class.java)
    }

    override fun getServersList(token: AuthToken, listener: ResponseListener<List<Server>>) {
        val call = clientCalls.getServers()
        call.enqueue(object : Callback<List<Server>> {
            override fun onResponse(call: Call<List<Server>>, response: Response<List<Server>>) {
                Log.e("Dtag", response.body().toString())
                listener.onResult(response.body())
            }

            override fun onFailure(call: Call<List<Server>>, t: Throwable) {
                Log.e("AuthClient", t.message!!)
            }

        })
    }
}