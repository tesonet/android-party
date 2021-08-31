package com.tesonet.testio.dagger.modules

import com.tesonet.testio.dagger.ApplicationSingleton
import com.tesonet.testio.managers.ServersManager
import com.tesonet.testio.service.repositories.ServersRepository
import dagger.Module
import dagger.Provides

@Module
class ManagersModule {

    @Provides
    @ApplicationSingleton
    fun provideServersManager(serversRepository: ServersRepository): ServersManager {
        return ServersManager(serversRepository)
    }
}