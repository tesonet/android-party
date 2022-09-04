package com.yasserakbbach.androidparty.listsevers.domain.di

import com.yasserakbbach.androidparty.listsevers.domain.repository.ServerRepository
import com.yasserakbbach.androidparty.listsevers.domain.usecase.GetAllServersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllServersUseCase(
        serverRepository: ServerRepository,
    ): GetAllServersUseCase =
        GetAllServersUseCase(serverRepository)
}