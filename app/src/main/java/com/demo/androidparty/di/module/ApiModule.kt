package com.demo.androidparty.di.module

import com.demo.androidparty.di.annotations.HttpClientAuthorized
import com.demo.androidparty.di.annotations.HttpClientUnauthorized
import com.demo.androidparty.di.annotations.InterceptorAuthorizationHeader
import com.demo.androidparty.network.ApiService
import com.demo.androidparty.network.interceptor.AuthorizationHeaderInterceptor
import com.demo.androidparty.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class ApiModule private constructor() {

    @Binds
    @InterceptorAuthorizationHeader
    internal abstract fun bindAuthorizationInterceptor(
        interceptor: AuthorizationHeaderInterceptor
    ): Interceptor

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        internal fun buildRetrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit =
            Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.ADDRESS)
                .build()

        @Provides
        @Singleton
        @HttpClientUnauthorized
        @JvmStatic
        fun provideUnauthorisedOkHttpClientBuilder(
            interceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        @Provides
        @Singleton
        @HttpClientAuthorized
        @JvmStatic
        internal fun provideAuthorisedOkHttpClientBuilder(
            interceptor: HttpLoggingInterceptor,
            @InterceptorAuthorizationHeader authInterceptor: Interceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(authInterceptor)
                .build()

        @Provides
        @Singleton
        @JvmStatic
        internal fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

        @Provides
        @Singleton
        @HttpClientAuthorized
        @JvmStatic
        internal fun provideAuthorisedApiService(
            @HttpClientAuthorized client: OkHttpClient
        ): ApiService = buildRetrofit(client).create(ApiService::class.java)

        @Provides
        @Singleton
        @HttpClientUnauthorized
        @JvmStatic
        internal fun provideUnauthorisedApiService(
            @HttpClientUnauthorized client: OkHttpClient
        ): ApiService = buildRetrofit(client).create(ApiService::class.java)

        private fun buildRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.ADDRESS)
                .build()
    }
}