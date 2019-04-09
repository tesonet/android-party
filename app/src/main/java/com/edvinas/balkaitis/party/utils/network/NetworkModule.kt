package com.edvinas.balkaitis.party.utils.network

import com.edvinas.balkaitis.party.BuildConfig
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.utils.schedulers.Io
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Module
    companion object {
        @JvmStatic @Singleton @Provides
        fun createRetrofit(client: OkHttpClient, @Io scheduler: Scheduler): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                    .build()
        }

        @JvmStatic @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
        }

        @JvmStatic @Provides
        fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)
    }
}
