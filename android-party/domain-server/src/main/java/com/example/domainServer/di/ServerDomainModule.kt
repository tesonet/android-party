package com.example.domainServer.di

import com.example.domainServer.domain.usecase.FetchServerUseCase
import com.example.domainServer.domain.usecase.FetchServerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServerDomainModule {
    @Binds
    fun bindFetchServer(useCase: FetchServerUseCaseImpl): FetchServerUseCase
}
