package com.playground.ugnius.homework.di.modules

import com.google.gson.GsonBuilder
import com.playground.ugnius.homework.model.clients.ApiClient
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = with(Retrofit.Builder()) {
        baseUrl("http://playground.tesonet.lt")
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        client(OkHttpClient().newBuilder().build())
        build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)

}