package com.yasserakbbach.androidparty.login.domain.di

import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import com.yasserakbbach.androidparty.login.domain.usecase.GetSessionUseCase
import com.yasserakbbach.androidparty.login.domain.usecase.LoginUseCase
import com.yasserakbbach.androidparty.login.domain.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
    ): LoginUseCase =
        LoginUseCase(loginRepository)

    @Provides
    fun provideGetSessionUseCase(
        sessionRepository: SessionRepository,
    ): GetSessionUseCase =
        GetSessionUseCase(sessionRepository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        sessionRepository: SessionRepository,
    ): LogoutUseCase =
        LogoutUseCase(sessionRepository)
}