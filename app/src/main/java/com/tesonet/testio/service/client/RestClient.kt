package com.tesonet.testio.service.client

import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {

    fun createClient(): ApiClient {
        return getRetrofit(createRetrofitBuilder()).create(ApiClient::class.java)
    }

    private fun createRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    private fun getRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit {
        return retrofitBuilder.client(Builder().build()).build()
    }

    companion object {
        const val API_BASE_URL: String = "https://playground.tesonet.lt"
    }
}