package com.baruckis.androidparty.ui.di

import android.app.Application
import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.local.LocalDataSourceImpl
import com.baruckis.androidparty.local.database.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LocalModule {

    @Module
    companion object {
        @Provides
        // If you use this annotation, the compiler will generate both a static method in the enclosing class of the
        // object and an instance method in the object itself.
        @JvmStatic
        fun provideDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindLocalSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}