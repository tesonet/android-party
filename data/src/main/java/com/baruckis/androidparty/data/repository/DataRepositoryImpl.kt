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

package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.mapper.LoggedInUserMapper
import com.baruckis.androidparty.data.mapper.ServerMapper
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.repository.DataRepository
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val loggedInUserMapper: LoggedInUserMapper,
    private val serverMapper: ServerMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DataRepository {

    override fun getLoggedInUser(): LoggedInUserEntity? {
        return localDataSource.getLoggedInUser()?.let { loggedInUserMapper.mapFromData(it) }
    }

    override fun login(username: String, password: String): Single<LoggedInUserEntity> {
        return remoteDataSource.sendAuthorization(username, password).map { tokenData: TokenData ->
            val loggedInUserData = LoggedInUserData(tokenData.token, username)
            localDataSource.setLoggedInUser(loggedInUserData)
            loggedInUserMapper.mapFromData(loggedInUserData)
        }
    }

    override fun logout() {
        localDataSource.setLoggedInUser(null)
    }

    override fun fetchServersFromRemoteApiSaveToDb(): Single<List<ServerEntity>> {

        return remoteDataSource.getServers().flatMap { serversList ->

            localDataSource.clearServers().andThen(
                localDataSource.saveServers(serversList).andThen(Single.just(serversList))
            )

        }.map { serversList ->
            serversList.map { server ->
                serverMapper.mapFromData(server)
            }
        }

    }

    override fun fetchServersFromLocalCache(): Single<List<ServerEntity>> {
        return localDataSource.getServers().map { serversList ->
            serversList.map { server -> serverMapper.mapFromData(server) }
        }
    }

}