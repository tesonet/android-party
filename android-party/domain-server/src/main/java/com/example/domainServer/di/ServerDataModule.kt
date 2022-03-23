package com.example.domainServer.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.BuildConfig.BASE_URL
import com.example.core.di.OtherInterceptorOkHttpClient
import com.example.domainServer.data.datasource.local.ServerLocalDataSource
import com.example.domainServer.data.datasource.local.ServerLocalDataSourceImpl
import com.example.domainServer.data.datasource.remote.ServerRemoteDataSource
import com.example.domainServer.data.datasource.remote.ServerRemoteDataSourceImpl
import com.example.domainServer.data.db.Constants.DATABASE_NAME
import com.example.domainServer.data.db.ServerDatabase
import com.example.domainServer.data.db.dao.ServerDao
import com.example.domainServer.data.interceptor.AuthHeaderInterceptor
import com.example.domainServer.data.repository.ServerRepositoryImpl
import com.example.domainServer.data.service.ServerListService
import com.example.domainServer.domain.repository.ServerRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ServerDataModule {
    @Binds
    fun bindRemoteDataSource(remoteSource: ServerRemoteDataSourceImpl): ServerRemoteDataSource

    @Binds
    fun bindLocalDataSource(localSource: ServerLocalDataSourceImpl): ServerLocalDataSource

    @Binds
    fun bindRepository(repository: ServerRepositoryImpl): ServerRepository

    @Binds
    fun bindAuthorizationInterceptor(
        interceptor: AuthHeaderInterceptor
    ): Interceptor

    companion object {
        private const val TIME_OUT = 30L

        @OtherInterceptorOkHttpClient
        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: Interceptor,
            @ApplicationContext context: Context
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(ChuckerInterceptor.Builder(context).build())
                .addInterceptor(interceptor)
                .build()
        }

        private val json = Json {
            ignoreUnknownKeys = true
        }

        @Singleton
        @Provides
        fun provideAPI(
            @OtherInterceptorOkHttpClient okHttpClient: OkHttpClient
        ): ServerListService {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    json.asConverterFactory(contentType)
                )
                .build()
                .create(ServerListService::class.java)
        }

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): ServerDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                ServerDatabase::class.java,
                DATABASE_NAME
            ).build()

        @Singleton
        @Provides
        fun provideDao(database: ServerDatabase): ServerDao = database.serverDao()
    }
}
