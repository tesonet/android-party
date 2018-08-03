package com.k4dima.androidparty.features.login.domain.di

import com.k4dima.androidparty.features.app.domain.UseCase
import com.k4dima.androidparty.features.login.domain.LoginUseCase
import com.k4dima.androidparty.features.login.ui.di.LoginScope
import dagger.Module
import dagger.Provides

@Module
object LoginUseCaseModule {
    @JvmStatic
    @LoginScope
    @Provides
    fun useCase(loginUseCase: LoginUseCase): UseCase<Array<String>, String> = loginUseCase
}