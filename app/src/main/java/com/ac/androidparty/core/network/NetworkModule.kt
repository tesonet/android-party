package com.ac.androidparty.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named(RetrofitConstants.BASE_OKHTTP)
    @Singleton
    fun provideOkHttpClient(
        commonInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptors(commonInterceptors)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Named(RetrofitConstants.BASE_RETROFIT)
    @Singleton
    fun provideRetrofit(
        @Named(RetrofitConstants.BASE_OKHTTP) client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.DEFAULT_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    @IntoSet
    internal fun provideLoginInterceptor(
        interceptor: LoginTokenInterceptor
    ): Interceptor = interceptor

    @Singleton
    @Provides
    @IntoSet
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}

object RetrofitConstants {
    const val BASE_RETROFIT = "BaseRetrofit"
    const val BASE_OKHTTP = "BaseOkHttp"
}