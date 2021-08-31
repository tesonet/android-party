package com.tesonet.testio.dagger.modules

import android.app.Application
import android.content.Context
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.client.RestClient
import com.tesonet.testio.service.repositories.ServersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule(private val application: Application) {

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return RestClient().createClient()
    }

    @Provides
    @Singleton
    fun provideServersRepository(apiClient: ApiClient): ServersRepository {
        return ServersRepository(apiClient)
    }
}