package com.example.domain_login.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.BuildConfig.BASE_URL
import com.example.core.di.AuthInterceptorOkHttpClient
import com.example.domain_login.data.datasource.local.LocalDataSource
import com.example.domain_login.data.datasource.local.LocalDataSourceImpl
import com.example.domain_login.data.datasource.remote.RemoteDataSource
import com.example.domain_login.data.datasource.remote.RemoteDataSourceImpl
import com.example.domain_login.data.datastore.LocalSharedPreference
import com.example.domain_login.data.repository.LoginRepositoryImpl
import com.example.domain_login.data.service.LoginService
import com.example.domain_login.domain.repository.LoginRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LoginDataModule {
    @Binds
    fun bindRemoteDataSource(useCase: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindLocalDataSource(useCase: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun bindRepository(useCase: LoginRepositoryImpl): LoginRepository

    companion object {
        private const val TIME_OUT = 30L

        @AuthInterceptorOkHttpClient
        @Singleton
        @Provides
        fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(ChuckerInterceptor.Builder(context).build())
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        private val json = Json {
            ignoreUnknownKeys = true
        }

        @Singleton
        @Provides
        fun provideAPI(
            @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
        ): LoginService {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    json.asConverterFactory(contentType)
                )
                .build()
                .create(LoginService::class.java)
        }

        @Singleton
        @Provides
        fun provideSharedPreference(preference: SharedPreferences): LocalSharedPreference {
            return LocalSharedPreference(preference)
        }

        @Singleton
        @Provides
        fun provideDefaultPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences(
                LocalSharedPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        }
    }
}