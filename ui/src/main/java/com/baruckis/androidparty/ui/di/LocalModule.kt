package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.local.LocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class LocalModule {

    @Binds
    abstract fun bindLocalSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}