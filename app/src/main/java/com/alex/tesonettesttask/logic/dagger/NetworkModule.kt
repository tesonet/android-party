package com.alex.tesonettesttask.logic.dagger

import com.alex.tesonettesttask.logic.network.TesonetService
import com.alex.tesonettesttask.logic.utils.Preferences
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideTesonetService(): TesonetService {
        return Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl("http://playground.tesonet.lt")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(TesonetService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getAuthInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    private fun getAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            val authToken = Preferences.getAuthToken()
            if (authToken.isNotEmpty())
                builder.addHeader("Authorization", "Bearer $authToken")
            chain.proceed(builder.build())
        }
    }
}
