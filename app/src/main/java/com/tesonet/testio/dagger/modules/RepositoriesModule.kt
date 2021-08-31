package com.tesonet.testio.dagger.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.client.RestClient
import com.tesonet.testio.service.database.ServersDao
import com.tesonet.testio.service.database.ServersDatabase
import com.tesonet.testio.service.repositories.ServersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule(private val application: Application) {

    private val serverDatabase: ServersDatabase = Room.databaseBuilder(
        application.baseContext,
        ServersDatabase::class.java,
        "servers_database"
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