package com.demo.androidparty.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.demo.androidparty.di.annotations.AppContext
import com.demo.androidparty.storage.database.AndroidPartyDatabase
import com.demo.androidparty.storage.database.ServerDao
import com.demo.androidparty.storage.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class StorageModule private constructor() {

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        internal fun provideSharedPreferencesManager(
            preferences: SharedPreferences
        ): AppPreferences = AppPreferences(preferences)

        @Provides
        @Singleton
        @JvmStatic
        internal fun provideDefaultSharedPreferences(
            @AppContext context: Context
        ): SharedPreferences =
            context.getSharedPreferences(AppPreferences.NAME, Context.MODE_PRIVATE)

        @Provides
        @Singleton
        @JvmStatic
        internal fun provideAndroidPartyDatabase(
            @AppContext context: Context
        ): AndroidPartyDatabase =
            Room.databaseBuilder(
                context,
                AndroidPartyDatabase::class.java,
                AndroidPartyDatabase.DB_NAME
            ).build()

        @Provides
        @Singleton
        @JvmStatic
        internal fun provideAndroidPartyDatabaseDao(database: AndroidPartyDatabase): ServerDao =
            database.dao()
    }
}