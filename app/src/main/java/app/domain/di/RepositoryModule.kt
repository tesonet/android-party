package app.domain.di

import app.domain.AppPreferenceRepository
import app.domain.PreferenceRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun providePreferenceRepository(appPreferenceRepository: AppPreferenceRepository):
            PreferenceRepository
}