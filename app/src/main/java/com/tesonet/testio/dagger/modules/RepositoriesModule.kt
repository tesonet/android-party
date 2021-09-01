package com.tesonet.testio.dagger.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tesonet.testio.services.client.ApiClient
import com.tesonet.testio.services.client.RestClient
import com.tesonet.testio.services.database.ServersDao
import com.tesonet.testio.services.database.ServersDatabase
import com.tesonet.testio.services.repositories.ServersRepository
import com.tesonet.testio.ui.MainActivity.Companion.SERVER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule(private val application: Application) {

    private val serverDatabase: ServersDatabase = Room.databaseBuilder(
        application.baseContext,
        ServersDatabase::class.java,
        SERVER_DATABASE_NAME
    ).build()

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return RestClient().createClient()
    }

    @Singleton
    @Provides
    fun provideServersDatabase(): ServersDatabase {
        return serverDatabase
    }

    @Singleton
    @Provides
    fun provideServersDao(): ServersDao {
        return serverDatabase.serversDao()
    }

    @Provides
    @Singleton
    fun provideServersRepository(apiClient: ApiClient, serversDao: ServersDao): ServersRepository {
        return ServersRepository(apiClient, serversDao)
    }
}