/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.data.repository.RemoteDataSource
import com.baruckis.androidparty.remote.RemoteDataSourceImpl
import com.baruckis.androidparty.remote.api.TesonetApiService
import com.baruckis.androidparty.remote.api.TesonetApiServiceFactory
import com.baruckis.androidparty.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    companion object {
        @Provides
        fun provideTesonetApi(localDataSource: LocalDataSource): TesonetApiService {
            return TesonetApiServiceFactory.createTesonetApiService(
                BuildConfig.DEBUG,
                localDataSource
            )
        }
    }

    @Binds
    abstract fun bindRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}