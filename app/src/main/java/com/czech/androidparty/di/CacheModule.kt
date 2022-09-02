package com.czech.androidparty.di

import com.czech.androidparty.Database
import com.czech.androidparty.datasource.cache.AndroidPartyCache
import com.czech.androidparty.datasource.cache.AndroidPartyCacheImpl
import com.czech.androidparty.datasource.cache.AndroidPartyDatabaseFactory
import com.czech.androidparty.datasource.cache.DriverFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideAndroidPartyDatabaseFactoryDriver(
        context: BaseApplication
    ): Database {
        return AndroidPartyDatabaseFactory(
            driverFactory = DriverFactory(context = context)
        ).createDriver()
    }

    @Provides
    @Singleton
    fun provideAndroidPartyCache(
        database: Database
    ): AndroidPartyCache {
        return AndroidPartyCacheImpl(
            database = database
        )
    }
}