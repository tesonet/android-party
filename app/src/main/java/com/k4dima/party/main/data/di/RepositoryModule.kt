package com.k4dima.androidparty.features.main.data.di

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.main.data.ServersRepository
import com.k4dima.androidparty.features.main.data.model.Server
import com.k4dima.androidparty.features.main.ui.di.MainScope
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