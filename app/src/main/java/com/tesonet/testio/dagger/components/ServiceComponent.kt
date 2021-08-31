package com.tesonet.testio.dagger.components

import com.tesonet.testio.dagger.modules.RepositoriesModule
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.repositories.ServersRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoriesModule::class]
)

interface ServiceComponent {
    fun getApiClient(): ApiClient
    fun getServerRepository(): ServersRepository
}