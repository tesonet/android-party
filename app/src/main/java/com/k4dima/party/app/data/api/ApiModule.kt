package com.k4dima.party.app.data.api

import com.k4dima.androidparty.features.app.data.api.TesonetService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    @Singleton
    @JvmStatic
    @Provides
    fun tesonetService(): TesonetService {
        return Retrofit.Builder()
                .baseUrl("http://playground.tesonet.lt/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TesonetService::class.java)
    }
}