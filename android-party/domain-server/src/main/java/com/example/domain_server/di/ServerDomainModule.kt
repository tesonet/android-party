package com.example.domain_server.di

import com.example.domain_server.domain.usecase.FetchServerUseCase
import com.example.domain_server.domain.usecase.FetchServerUseCaseImpl
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