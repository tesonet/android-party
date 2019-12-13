package com.giedrius.androidparty.task.api.servers

import android.util.Log
import com.giedrius.androidparty.task.viewmodel.Server
import com.giedrius.androidparty.task.api.ApiClient
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServersClientImplementation(private val token: String): ServersClient {
    private var okClient: OkHttpClient
    private var retrofit: Retrofit
    private var mApiClient: ApiClient

    init {
        okClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val baseRequest: Request = chain.request()
            val requestBuilder = baseRequest.newBuilder().header(
                Constants.SERVERS_HEADER_NAME, "${Constants.SERVERS_HEADER_VALUE} $token")
                .method(baseRequest.method(), baseRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiClient = retrofit.create(ApiClient::class.java)
    }

    override fun getServersList(apiListener: ApiListener<List<Server>>) {
        val call: Call<List<Server>> = mApiClient.getServers()
        call.enqueue(object : Callback<List<Server>> {
            override fun onResponse(call: Call<List<Server>>, response: Response<List<Server>>) {
                apiListener.onResult(response.body())
            }

            override fun onFailure(call: Call<List<Server>>, error: Throwable) {
                error.message.let {
                    Log.e("Servers call failed", it)
                }
            }
        })
    }
}