package main.domain.di

import app.domain.DataRepository
import main.domain.ServersRepository
import main.domain.model.Server
import main.ui.di.MainScope
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