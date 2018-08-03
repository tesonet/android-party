package com.k4dima.androidparty.features.app.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.k4dima.androidparty.features.app.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}