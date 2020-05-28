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