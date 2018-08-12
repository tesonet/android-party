package com.teso.net.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teso.net.BuildConfig
import com.teso.net.data_flow.interactions.ITokenInteractor
import com.teso.net.data_flow.network.Api
import com.teso.net.data_flow.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): Api = retrofit.create(Api::class.java)


    @Singleton
    @Provides
    fun provideBaseRetrofit(httpUrl: HttpUrl,
                            client: OkHttpClient,
                            gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpUrl() = HttpUrl.parse(BuildConfig.API_BASE_URL)!!

    @Singleton
    @Provides
    fun provideOkHttpClient(loggerInterceptor: HttpLoggingInterceptor,
                            tokenInterceptor: TokenInterceptor,
                            headersInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .addInterceptor(tokenInterceptor)
            .addNetworkInterceptor(loggerInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag(NETWORK_TAG).d(it)
        })
        logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return logger
    }

    @Singleton
    @Provides
    fun provideTokenInterceptor(loginInteractor: ITokenInteractor): TokenInterceptor {
        return TokenInterceptor(loginInteractor)
    }

    @Singleton
    @Provides
    fun provideHeadersInterceptor() = Interceptor {
        it.proceed(
                it.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Accept-Language", "en")
                        .addHeader("Content-Type", "application/json")
                        .build()
        )
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .serializeNulls()
            .create()

    companion object {
        private const val NETWORK_TAG = "Network"
    }
}