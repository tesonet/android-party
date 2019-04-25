package com.k4dima.party.login.domain.di

import com.k4dima.party.app.domain.UseCase
import com.k4dima.party.login.domain.LoginUseCase
import com.k4dima.party.login.ui.di.LoginScope
import dagger.Module
import dagger.Provides

@Module
object LoginUseCaseModule {
    @JvmStatic
    @LoginScope
    @Provides
    fun useCase(loginUseCase: LoginUseCase): UseCase<Array<String>, String> = loginUseCase
}