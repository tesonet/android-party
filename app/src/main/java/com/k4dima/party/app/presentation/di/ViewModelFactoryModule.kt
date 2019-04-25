package com.k4dima.party.app.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.k4dima.party.app.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}