package com.yasserakbbach.androidparty.login.domain.di

import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository
import com.yasserakbbach.androidparty.login.domain.usecase.LoginUseCase
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
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
    ): LoginUseCase =
        LoginUseCase(loginRepository)
}