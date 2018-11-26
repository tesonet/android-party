package com.tesonet.testio.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "testio.db").build()

    @Singleton
    @Provides
    fun provideCredentialsDao(database: AppDatabase) = database.credentialsDao()

    @Singleton
    @Provides
    fun provideServerDao(database: AppDatabase) = database.serverDao()

    @Singleton
    @Provides
    fun proviceTokenDao(database: AppDatabase) = database.tokenDao()
}