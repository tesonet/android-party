package com.ac.androidparty.servers.data.di

import android.content.Context
import androidx.room.Room
import com.ac.androidparty.servers.data.localsource.ServersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServersPersistenceModule {

    @Provides
    @Singleton
    internal fun provideServersDatabase(@ApplicationContext context: Context): ServersDatabase =
        Room.databaseBuilder(context, ServersDatabase::class.java, SERVERS_FILE_NAME).build()

    @Provides
    @Singleton
    internal fun provideServersDao(serversDatabase: ServersDatabase) = serversDatabase.serversDao()


    private const val SERVERS_FILE_NAME = "servers.db"
}