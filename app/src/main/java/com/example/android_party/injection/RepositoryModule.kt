package com.example.android_party.injection

import com.example.android_party.ui.login.LoginRepository
import com.example.android_party.ui.servers.ServersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object RepositoryModule {

    /**
     * Provides the Login Repository implementation.
     * @return the Login Repository implementation.
     */
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideLoginRepository(): LoginRepository {
        return LoginRepository()
    }

    /**
     * Provides the Server Repository implementation.
     * @return the Server Repository implementation.
     */
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideServersRepository(): ServersRepository {
        return ServersRepository()
    }
}