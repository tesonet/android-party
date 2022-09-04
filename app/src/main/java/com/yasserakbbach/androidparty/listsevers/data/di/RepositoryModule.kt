package com.yasserakbbach.androidparty.listsevers.data.di

import com.yasserakbbach.androidparty.listsevers.data.local.AppDatabase
import com.yasserakbbach.androidparty.listsevers.data.remote.ServerApi
import com.yasserakbbach.androidparty.listsevers.data.repository.ServerRepositoryImpl
import com.yasserakbbach.androidparty.listsevers.domain.repository.ServerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideServerApi(
        retrofit: Retrofit,
    ): ServerApi =
        retrofit.create()

    @Provides
    @Singleton
    fun provideServerRepository(
        serverApi: ServerApi,
        appDatabase: AppDatabase,
    ): ServerRepository =
        ServerRepositoryImpl(
            serverApi,
            appDatabase,
        )
}