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

import com.baruckis.androidparty.data.DataTestDataFactory
import com.baruckis.androidparty.data.mapper.LoggedInUserMapper
import com.baruckis.androidparty.data.mapper.ServerMapper
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.entity.ServerEntity
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock

class DataRepositoryImplTest {

    private val loggedInUserMapper = mock(LoggedInUserMapper::class.java)
    private val serverMapper = mock(ServerMapper::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)

    private val dataRepository =
        DataRepositoryImpl(
            loggedInUserMapper,
            serverMapper,
            localDataSource,
            remoteDataSource
        )

    private val tokenData = DataTestDataFactory.createTokenData()

    private val loggedInUserData = DataTestDataFactory.createLoggedInUserData()
    private val loggedInUserEntity = DataTestDataFactory.createLoggedInUserEntity()

    @Before
    fun setup() {
        stubRemoteSendAuthorization(
            anyString(),
            anyString(),
            Single.just(tokenData)
        )
        stubSetLoggedInUser()
        stubLoggedInUserMapperMapFromData(loggedInUserData, loggedInUserEntity)

        stubLocalClearServers()
        stubLocalSaveServers(MockitoHelper.anyObject())
    }


    @Test
    fun loginCompletes() {
        val testObserver =
            dataRepository.login(DataTestDataFactory.username, DataTestDataFactory.password).test()
        testObserver.assertComplete()
    }

    @Test
    fun loginReturnsData() {
        val testObserver =
            dataRepository.login(DataTestDataFactory.username, DataTestDataFactory.password).test()
        testObserver.assertValue(loggedInUserEntity)
    }

    @Test
    fun fetchServersFromRemoteApiSaveToDbCompletes() {
        stubRemoteGetServers(Single.just(listOf(DataTestDataFactory.createServerData())))
        stubServerMapperMapFromData(MockitoHelper.anyObject(), DataTestDataFactory.createServerEntity())

        val testObserver =
            dataRepository.fetchServersFromRemoteApiSaveToDb().test()
        testObserver.assertComplete()
    }

    @Test
    fun fetchServersFromRemoteApiSaveToDbReturnsData() {
        val serverData = DataTestDataFactory.createServerData()
        val serverEntity = DataTestDataFactory.createServerEntity()

        stubRemoteGetServers(Single.just(listOf(serverData)))
        stubServerMapperMapFromData(serverData, serverEntity)

        val testObserver =
            dataRepository.fetchServersFromRemoteApiSaveToDb().test()
        testObserver.assertValue(listOf(serverEntity))
    }

    @Test
    fun fetchServersFromLocalCacheCompletes() {
        stubLocalGetServers(Single.just(listOf(DataTestDataFactory.createServerData())))
        stubServerMapperMapFromData(MockitoHelper.anyObject(), DataTestDataFactory.createServerEntity())

        val testObserver =
            dataRepository.fetchServersFromLocalCache().test()
        testObserver.assertComplete()
    }

    @Test
    fun fetchServersFromLocalCacheReturnsData() {
        val serverData = DataTestDataFactory.createServerData()
        val serverEntity = DataTestDataFactory.createServerEntity()

        stubLocalGetServers(Single.just(listOf(serverData)))
        stubServerMapperMapFromData(serverData, serverEntity)

        val testObserver =
            dataRepository.fetchServersFromLocalCache().test()
        testObserver.assertValue(listOf(serverEntity))
    }


    private fun stubLoggedInUserMapperMapFromData(
        dataModel: LoggedInUserData,
        domainEntity: LoggedInUserEntity
    ) {
        Mockito.`when`(loggedInUserMapper.mapFromData(dataModel))
            .thenReturn(domainEntity)
    }

    private fun stubRemoteSendAuthorization(
        username: String,
        password: String,
        response: Single<TokenData>
    ) {
        Mockito.`when`(remoteDataSource.sendAuthorization(username, password)).thenReturn(response)
    }

    private fun stubSetLoggedInUser() {
        doNothing().`when`(localDataSource).setLoggedInUser(any())
    }

    private fun stubRemoteGetServers(single: Single<List<ServerData>>) {
        Mockito.`when`(remoteDataSource.getServers()).thenReturn(single)
    }

    private fun stubLocalClearServers() {
        Mockito.`when`(localDataSource.clearServers()).thenReturn(Completable.complete())
    }

    private fun stubLocalSaveServers(list: List<ServerData>) {
        Mockito.`when`(localDataSource.saveServers(list)).thenReturn(Completable.complete())
    }

    private fun stubLocalGetServers(single: Single<List<ServerData>>) {
        Mockito.`when`(localDataSource.getServers()).thenReturn(single)
    }

    private fun stubServerMapperMapFromData(
        dataModel: ServerData,
        domainEntity: ServerEntity
    ) {
        Mockito.`when`(serverMapper.mapFromData(dataModel))
            .thenReturn(domainEntity)
    }

    // Matches any object, excluding nulls.
    object MockitoHelper {
        fun <T> anyObject(): T {
            Mockito.any<T>()
            return uninitialized()
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> uninitialized(): T = null as T
    }

}