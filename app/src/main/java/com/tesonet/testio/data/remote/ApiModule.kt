package com.tesonet.testio.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(PlaygroundApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

    @Provides
    @Singleton
    fun providePlaygroundApi(retrofit: Retrofit): PlaygroundApi =
            retrofit.create(PlaygroundApi::class.java)
}