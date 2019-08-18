package com.k4dima.party.app.data.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    @Singleton
    @JvmStatic
    @Provides
    fun tesonetService(): TesonetService = Retrofit.Builder()
            .baseUrl("http://playground.tesonet.lt/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TesonetService::class.java)
}