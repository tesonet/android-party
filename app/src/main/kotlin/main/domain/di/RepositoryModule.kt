package main.domain.di

import app.domain.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import main.domain.ServersRepository
import main.domain.model.Server

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @ActivityRetainedScoped
    @Provides
    fun provideServersRepository(serversRepository: ServersRepository):
            DataRepository<String, List<Server>> = serversRepository
}