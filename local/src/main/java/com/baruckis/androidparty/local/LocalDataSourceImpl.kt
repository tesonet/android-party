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

package com.baruckis.androidparty.local

import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.local.database.AppDatabase
import com.baruckis.androidparty.local.mapper.LocalServerMapper
import com.baruckis.androidparty.local.preferences.PreferenceStorage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceStorage: PreferenceStorage,
    private val appDatabase: AppDatabase,
    private val localServerMapper: LocalServerMapper
) : LocalDataSource {

    override fun getToken(): String {
        return sharedPreferenceStorage.token ?: ""
    }

    override fun getLoggedInUser(): LoggedInUserData? {
        val token = sharedPreferenceStorage.token
        val username = sharedPreferenceStorage.username
        return if (token.isNullOrBlank() || username.isNullOrBlank()) null else
            LoggedInUserData(token, username)
    }

    override fun setLoggedInUser(user: LoggedInUserData?) {
        sharedPreferenceStorage.token = user?.token
        sharedPreferenceStorage.username = user?.username
    }

    override fun getServers(): Single<List<ServerData>> {
        return appDatabase.serverLocalDao().getServers().firstOrError().map { serversList ->
            serversList.map { server -> localServerMapper.mapFromLocal(server) }
        }
    }

    override fun clearServers(): Completable {
        return Completable.defer {
            appDatabase.serverLocalDao().deleteServers()
            Completable.complete()
        }
    }

    override fun saveServers(serversList: List<ServerData>): Completable {
        return Completable.defer {
            appDatabase.serverLocalDao()
                .saveServers(serversList.map { server -> localServerMapper.mapToLocal(server) })
            Completable.complete()
        }
    }

}