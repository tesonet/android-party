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

package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.DomainTestDataFactory
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.DataRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FetchServersUseCaseTest {

    private lateinit var fetchServersUseCase: FetchServersUseCase

    @Mock
    lateinit var dataRepository: DataRepository

    @Mock
    @Background
    lateinit var backgroundScheduler: Scheduler

    @Mock
    @Foreground
    lateinit var foregroundScheduler: Scheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        fetchServersUseCase =
            FetchServersUseCase(
                dataRepository,
                backgroundScheduler,
                foregroundScheduler
            )
    }

    @Test(expected = IllegalArgumentException::class)
    fun loginThrowsException() {
        fetchServersUseCase.buildUseCaseSingle().test()
    }

    @Test
    fun fetchServersFromLocalCacheCompletes() {
        stubDataRepositoryFetchServersFromLocalCache(Single.just(listOf(DomainTestDataFactory.createServerEntity())))

        val testObserver = fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.LOCAL
            )
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun fetchServersFromLocalCacheReturnsData() {
        val serverEntity = DomainTestDataFactory.createServerEntity()
        stubDataRepositoryFetchServersFromLocalCache(Single.just(listOf(serverEntity)))

        val testObserver = fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.LOCAL
            )
        ).test()
        testObserver.assertValue(listOf(serverEntity))
    }

    @Test
    fun fetchServersFromLocalCacheCallsRepository() {
        stubDataRepositoryFetchServersFromLocalCache(Single.just(listOf(DomainTestDataFactory.createServerEntity())))

        fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.LOCAL
            )
        ).test()
        Mockito.verify(dataRepository).fetchServersFromLocalCache()
    }

    @Test
    fun fetchServersFromRemoteApiSaveToDbCompletes() {
        stubDataRepositoryFetchServersFromRemoteApiSaveToDb(Single.just(listOf(DomainTestDataFactory.createServerEntity())))

        val testObserver = fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.REMOTE
            )
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun fetchServersFromRemoteApiSaveToDbReturnsData() {
        val serverEntity = DomainTestDataFactory.createServerEntity()
        stubDataRepositoryFetchServersFromRemoteApiSaveToDb(Single.just(listOf(serverEntity)))

        val testObserver = fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.REMOTE
            )
        ).test()
        testObserver.assertValue(listOf(serverEntity))
    }

    @Test
    fun fetchServersFromRemoteApiSaveToDbCallsRepository() {
        stubDataRepositoryFetchServersFromRemoteApiSaveToDb(Single.just(listOf(DomainTestDataFactory.createServerEntity())))

        fetchServersUseCase.buildUseCaseSingle(
            FetchServersUseCase.Params.dataSource(
                FetchServersUseCase.DataSource.REMOTE
            )
        ).test()
        Mockito.verify(dataRepository).fetchServersFromRemoteApiSaveToDb()
    }


    private fun stubDataRepositoryFetchServersFromLocalCache(single: Single<List<ServerEntity>>) {
        Mockito.`when`(dataRepository.fetchServersFromLocalCache())
            .thenReturn(single)
    }

    private fun stubDataRepositoryFetchServersFromRemoteApiSaveToDb(single: Single<List<ServerEntity>>) {
        Mockito.`when`(dataRepository.fetchServersFromRemoteApiSaveToDb())
            .thenReturn(single)
    }

}