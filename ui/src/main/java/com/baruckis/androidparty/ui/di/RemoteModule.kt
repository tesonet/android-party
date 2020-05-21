package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.data.repository.RemoteDataSource
import com.baruckis.androidparty.remote.RemoteDataSourceImpl
import com.baruckis.androidparty.remote.api.TesonetApiService
import com.baruckis.androidparty.remote.api.TesonetApiServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideTesonetApi(localDataSource: LocalDataSource): TesonetApiService {
            return TesonetApiServiceFactory.createTesonetApiService(true, localDataSource)
        }
    }

    @Binds
    abstract fun bindRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}