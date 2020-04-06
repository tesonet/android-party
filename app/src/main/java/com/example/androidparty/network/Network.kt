package com.example.androidparty.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Network : NetworkProvider {
    val BASE_URL = "http://playground.tesonet.lt"

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())
            .build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    override fun getApi(): Api {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(Api::class.java)
    }

}