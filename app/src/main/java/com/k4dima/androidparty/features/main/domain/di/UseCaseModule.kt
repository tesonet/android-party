package com.k4dima.androidparty.features.main.domain.di

import com.k4dima.androidparty.features.main.domain.MainUseCase
import com.k4dima.androidparty.features.main.domain.ServersUseCase
import com.k4dima.androidparty.features.main.ui.di.MainScope
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {
    @JvmStatic
    @MainScope
    @Provides
    fun useCase(mainUseCase: MainUseCase): ServersUseCase = mainUseCase
}