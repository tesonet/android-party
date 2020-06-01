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

package com.baruckis.androidparty.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.usecases.FetchServersUseCase
import com.baruckis.androidparty.domain.usecases.LogoutUseCase
import com.baruckis.androidparty.presentation.PresentationTestDataFactory
import com.baruckis.androidparty.presentation.TestCoroutineContextProvider
import com.baruckis.androidparty.presentation.mapper.ServerPresentationMapper
import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.presentation.util.any
import com.baruckis.androidparty.presentation.util.argumentCaptor
import com.baruckis.androidparty.presentation.util.capture
import com.baruckis.androidparty.presentation.util.eq
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.times
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testContextProvider =
        TestCoroutineContextProvider()
    private val logoutUseCase: LogoutUseCase = Mockito.mock(LogoutUseCase::class.java)
    private val fetchServersUseCase: FetchServersUseCase =
        Mockito.mock(FetchServersUseCase::class.java)
    private val serverPresentationMapper: ServerPresentationMapper =
        Mockito.mock(ServerPresentationMapper::class.java)

    private val viewModel = MainViewModel(
        testContextProvider,
        logoutUseCase,
        fetchServersUseCase,
        serverPresentationMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableSingleObserver<List<ServerEntity>>>()


    @Test
    fun fetchServersLocallyExecutesUseCase() {

        viewModel.fetchServersLocally()

        Mockito.verify(fetchServersUseCase, times(1)).execute(
            any(),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.LOCAL)
            )
        )
    }

    @Test
    fun fetchServersLocallyReturnsSuccess() {

        val serverEntity = PresentationTestDataFactory.createServerEntity()
        val serverPresentation = PresentationTestDataFactory.createServerPresentation()

        stubServerPresentationMapperMapToPresentation(serverEntity, serverPresentation)

        viewModel.fetchServersLocally()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.LOCAL)
            )
        )

        captor.value.onSuccess(listOf(serverEntity))

        assertEquals(Status.SUCCESS, viewModel.serversResource.value?.status)

    }

    @Test
    fun fetchServersLocallyReturnsData() {

        val serverEntity = PresentationTestDataFactory.createServerEntity()
        val serverPresentation = PresentationTestDataFactory.createServerPresentation()

        stubServerPresentationMapperMapToPresentation(serverEntity, serverPresentation)

        viewModel.fetchServersLocally()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.LOCAL)
            )
        )

        captor.value.onSuccess(listOf(serverEntity))

        assertEquals(listOf(serverPresentation), viewModel.serversResource.value?.data)

    }

    @Test
    fun fetchServersLocallyReturnsError() {

        viewModel.fetchServersLocally()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.LOCAL)
            )
        )

        captor.value.onError(RuntimeException())

        assertEquals(Status.ERROR, viewModel.serversResource.value?.status)
    }

    @Test
    fun fetchServersLocallyReturnsErrorMessage() {

        val errorMsg = PresentationTestDataFactory.createErrorMessage()

        viewModel.fetchServersLocally()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.LOCAL)
            )
        )

        captor.value.onError(RuntimeException(errorMsg))

        assertEquals(errorMsg, viewModel.serversResource.value?.message)
    }

    @Test
    fun fetchServersRemotelyExecutesUseCase() {

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        Mockito.verify(fetchServersUseCase, times(1)).execute(
            any(),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )
    }

    @Test
    fun fetchServersRemotelyReturnsSuccess() {

        val serverEntity = PresentationTestDataFactory.createServerEntity()
        val serverPresentation = PresentationTestDataFactory.createServerPresentation()

        stubServerPresentationMapperMapToPresentation(serverEntity, serverPresentation)

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captor.value.onSuccess(listOf(serverEntity))

        assertEquals(Status.SUCCESS, viewModel.serversResource.value?.status)

    }

    @Test
    fun fetchServersRemotelyReturnsData() {

        val serverEntity = PresentationTestDataFactory.createServerEntity()
        val serverPresentation = PresentationTestDataFactory.createServerPresentation()

        stubServerPresentationMapperMapToPresentation(serverEntity, serverPresentation)

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captor.value.onSuccess(listOf(serverEntity))

        assertEquals(listOf(serverPresentation), viewModel.serversResource.value?.data)

    }

    @Test
    fun fetchServersRemotelyReturnsError() {

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captor.value.onError(RuntimeException())

        assertEquals(Status.ERROR, viewModel.serversResource.value?.status)
    }

    @Test
    fun fetchServersRemotelyReturnsErrorMessage() {

        val errorMsg = PresentationTestDataFactory.createErrorMessage()

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        Mockito.verify(fetchServersUseCase).execute(
            capture(captor),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captor.value.onError(RuntimeException(errorMsg))

        assertEquals(errorMsg, viewModel.serversResource.value?.message)
    }


    private fun stubServerPresentationMapperMapToPresentation(
        domainEntity: ServerEntity,
        presentationModel: ServerPresentation
    ) {
        Mockito.`when`(serverPresentationMapper.mapToPresentation(domainEntity))
            .thenReturn(presentationModel)
    }

}