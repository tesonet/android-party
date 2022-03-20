package com.example.domain_login.di

import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.core.dispatcher.MainDispatcherProvider
import com.example.domain_login.domain.usecase.LoginUseCase
import com.example.domain_login.domain.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase

    companion object {
        @Singleton
        @Provides
        fun provideDispatcher(): BaseDispatcherProvider {
            return MainDispatcherProvider()
        }
    }
}