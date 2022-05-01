package com.czech.androidparty.di

import com.czech.androidparty.datasource.network.ApiService
import com.czech.androidparty.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: ApiService
    ): LoginRepository {
        return LoginRepository(
            apiService = apiService
        )
    }
}