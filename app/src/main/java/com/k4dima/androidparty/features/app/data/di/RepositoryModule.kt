package com.k4dima.androidparty.features.app.data.di

import com.k4dima.androidparty.features.app.data.AppPreferenceRepository
import com.k4dima.androidparty.features.app.data.PreferenceRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun providePreferenceRepository(appPreferenceRepository: AppPreferenceRepository): PreferenceRepository
}