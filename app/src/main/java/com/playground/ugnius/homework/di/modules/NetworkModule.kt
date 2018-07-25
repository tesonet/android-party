package com.playground.ugnius.homework.di.modules

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.playground.ugnius.homework.model.clients.ApiClient
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [UtilitiesModule::class])
class NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder().apply { setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES) }
        return GsonConverterFactory.create(builder.create())
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory) = with(Retrofit.Builder()) {
        baseUrl("http://playground.tesonet.lt")
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        addConverterFactory(gsonConverterFactory)
        client(OkHttpClient().newBuilder().build())
        build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit) = retrofit.create(ApiClient::class.java)

}