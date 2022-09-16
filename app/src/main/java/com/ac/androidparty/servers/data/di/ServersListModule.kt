package com.ac.androidparty.servers.data.di

import com.ac.androidparty.core.network.RetrofitConstants
import com.ac.androidparty.servers.data.remote.ServersApi
import com.ac.androidparty.servers.data.repository.ServersListRepository
import com.ac.androidparty.servers.data.repository.ServersListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServersListModule {
    @Provides
    @Singleton
    internal fun providesServersApi(
        @Named(RetrofitConstants.BASE_RETROFIT) retrofit: Retrofit): ServersApi =
        retrofit.newBuilder().build().create()

    @Provides
    internal fun provideServersListRepository(
        serversListRepositoryImpl: ServersListRepositoryImpl
    ): ServersListRepository = serversListRepositoryImpl

}