package app.domain.di

import app.domain.AppPreferenceRepository
import app.domain.PreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun providePreferenceRepository(appPreferenceRepository: AppPreferenceRepository):
            PreferenceRepository
}