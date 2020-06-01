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

package com.baruckis.androidparty.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.usecases.FetchServersUseCase
import com.baruckis.androidparty.domain.usecases.LoginUseCase
import com.baruckis.androidparty.presentation.PresentationTestDataFactory
import com.baruckis.androidparty.presentation.TestCoroutineContextProvider
import com.baruckis.androidparty.presentation.mapper.LoginPresentationMapper
import com.baruckis.androidparty.presentation.mapper.ServerPresentationMapper
import com.baruckis.androidparty.presentation.model.LoginPresentation
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
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testContextProvider = TestCoroutineContextProvider()
    private val loginUseCase: LoginUseCase = mock(LoginUseCase::class.java)
    private val fetchServersUseCase: FetchServersUseCase = mock(FetchServersUseCase::class.java)
    private val loginPresentationMapper: LoginPresentationMapper =
        mock(LoginPresentationMapper::class.java)
    private val serverPresentationMapper: ServerPresentationMapper =
        mock(ServerPresentationMapper::class.java)

    private val viewModel = LoginViewModel(
        testContextProvider,
        loginUseCase,
        fetchServersUseCase,
        loginPresentationMapper,
        serverPresentationMapper
    )

    @Captor
    val captorLogin = argumentCaptor<DisposableSingleObserver<LoggedInUserEntity>>()

    @Captor
    val captorFetchServers = argumentCaptor<DisposableSingleObserver<List<ServerEntity>>>()


    @Test
    fun loginExecutesUseCase() {

        viewModel.login(
            PresentationTestDataFactory.username,
            PresentationTestDataFactory.password,
            0
        )

        verify(loginUseCase, times(1)).execute(
            any(),
            eq(
                LoginUseCase.Params.authorization(
                    PresentationTestDataFactory.username,
                    PresentationTestDataFactory.password
                )
            )
        )

    }

    @Test
    fun loginReturnsSuccess() {

        val loggedInUserEntity = PresentationTestDataFactory.createLoggedInUserEntity()
        val loginPresentation = PresentationTestDataFactory.createLoginPresentation()

        stubLoginPresentationMapperMapToPresentation(loggedInUserEntity, loginPresentation)

        viewModel.login(
            PresentationTestDataFactory.username,
            PresentationTestDataFactory.password,
            0
        )

        verify(loginUseCase).execute(
            capture(captorLogin),
            eq(
                LoginUseCase.Params.authorization(
                    PresentationTestDataFactory.username,
                    PresentationTestDataFactory.password
                )
            )
        )

        captorLogin.value.onSuccess(loggedInUserEntity)

        assertEquals(Status.SUCCESS, viewModel.loginResource.value?.status)

    }

    @Test
    fun loginReturnsData() {

        val loggedInUserEntity = PresentationTestDataFactory.createLoggedInUserEntity()
        val loginPresentation = PresentationTestDataFactory.createLoginPresentation()

        stubLoginPresentationMapperMapToPresentation(loggedInUserEntity, loginPresentation)

        viewModel.login(
            PresentationTestDataFactory.username,
            PresentationTestDataFactory.password,
            0
        )

        verify(loginUseCase).execute(
            capture(captorLogin),
            eq(
                LoginUseCase.Params.authorization(
                    PresentationTestDataFactory.username,
                    PresentationTestDataFactory.password
                )
            )
        )

        captorLogin.value.onSuccess(loggedInUserEntity)

        assertEquals(loginPresentation, viewModel.loginResource.value?.data)

    }

    @Test
    fun loginReturnsError() {

        viewModel.login(
            PresentationTestDataFactory.username,
            PresentationTestDataFactory.password,
            0
        )

        verify(loginUseCase).execute(
            capture(captorLogin),
            eq(
                LoginUseCase.Params.authorization(
                    PresentationTestDataFactory.username,
                    PresentationTestDataFactory.password
                )
            )
        )

        captorLogin.value.onError(RuntimeException())

        assertEquals(Status.ERROR, viewModel.loginResource.value?.status)
    }

    @Test
    fun loginReturnsErrorMessage() {

        val errorMsg = PresentationTestDataFactory.createErrorMessage()

        viewModel.login(
            PresentationTestDataFactory.username,
            PresentationTestDataFactory.password,
            0
        )

        verify(loginUseCase).execute(
            capture(captorLogin),
            eq(
                LoginUseCase.Params.authorization(
                    PresentationTestDataFactory.username,
                    PresentationTestDataFactory.password
                )
            )
        )

        captorLogin.value.onError(RuntimeException(errorMsg))

        assertEquals(errorMsg, viewModel.loginResource.value?.message)
    }


    @Test
    fun fetchServersRemotelyExecutesUseCase() {

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        verify(fetchServersUseCase, times(1)).execute(
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

        verify(fetchServersUseCase).execute(
            capture(captorFetchServers),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captorFetchServers.value.onSuccess(listOf(serverEntity))

        assertEquals(Status.SUCCESS, viewModel.serversResource.value?.status)

    }

    @Test
    fun fetchServersRemotelyReturnsData() {

        val serverEntity = PresentationTestDataFactory.createServerEntity()
        val serverPresentation = PresentationTestDataFactory.createServerPresentation()

        stubServerPresentationMapperMapToPresentation(serverEntity, serverPresentation)

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        verify(fetchServersUseCase).execute(
            capture(captorFetchServers),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captorFetchServers.value.onSuccess(listOf(serverEntity))

        assertEquals(listOf(serverPresentation), viewModel.serversResource.value?.data)

    }

    @Test
    fun fetchServersRemotelyReturnsError() {

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        verify(fetchServersUseCase).execute(
            capture(captorFetchServers),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captorFetchServers.value.onError(RuntimeException())

        assertEquals(Status.ERROR, viewModel.serversResource.value?.status)
    }

    @Test
    fun fetchServersRemotelyReturnsErrorMessage() {

        val errorMsg = PresentationTestDataFactory.createErrorMessage()

        viewModel.fetchServersRemotely(0)

        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

        verify(fetchServersUseCase).execute(
            capture(captorFetchServers),
            eq(
                FetchServersUseCase.Params.dataSource(FetchServersUseCase.DataSource.REMOTE)
            )
        )

        captorFetchServers.value.onError(RuntimeException(errorMsg))

        assertEquals(errorMsg, viewModel.serversResource.value?.message)
    }


    private fun stubLoginPresentationMapperMapToPresentation(
        domainEntity: LoggedInUserEntity,
        presentationModel: LoginPresentation
    ) {
        `when`(loginPresentationMapper.mapToPresentation(domainEntity))
            .thenReturn(presentationModel)
    }

    private fun stubServerPresentationMapperMapToPresentation(
        domainEntity: ServerEntity,
        presentationModel: ServerPresentation
    ) {
        `when`(serverPresentationMapper.mapToPresentation(domainEntity))
            .thenReturn(presentationModel)
    }

}