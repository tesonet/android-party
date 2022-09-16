package com.ac.androidparty.servers.data.di

import android.content.Context
import com.ac.androidparty.core.network.RetrofitConstants
import com.ac.androidparty.servers.data.remote.ServersApi
import com.ac.androidparty.servers.data.repository.cachedserverslist.CachedServersListRepository
import com.ac.androidparty.servers.data.repository.cachedserverslist.CachedServersListRepositoryImpl
import com.ac.androidparty.servers.data.repository.serverslist.ServersListRepository
import com.ac.androidparty.servers.data.repository.serverslist.ServersListRepositoryImpl
import com.ac.androidparty.servers.domain.usecase.GetServersUseCase
import com.ac.androidparty.servers.domain.usecase.GetServersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
        @Named(RetrofitConstants.BASE_RETROFIT) retrofit: Retrofit
    ): ServersApi =
        retrofit.newBuilder().build().create()

    @Provides
    internal fun provideServersListRepository(
        serversListRepositoryImpl: ServersListRepositoryImpl
    ): ServersListRepository = serversListRepositoryImpl

    @Provides
    internal fun provideServersListUseCase(
        serversUseCaseImpl: GetServersUseCaseImpl
    ): GetServersUseCase = serversUseCaseImpl

    @Provides
    internal fun provideCachedServersListRepository(
        cachedServersListRepositoryImpl: CachedServersListRepositoryImpl
    ): CachedServersListRepository = cachedServersListRepositoryImpl

}