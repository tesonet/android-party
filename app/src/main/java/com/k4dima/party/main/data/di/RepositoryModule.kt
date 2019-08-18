package com.k4dima.party.main.data.di

import com.k4dima.party.app.data.DataRepository
import com.k4dima.party.main.data.ServersRepository
import com.k4dima.party.main.data.model.Server
import com.k4dima.party.main.ui.di.MainScope
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @JvmStatic
    @MainScope
    @Provides
    fun provideServersRepository(serversRepository: ServersRepository):
            DataRepository<String, List<Server>> = serversRepository
}