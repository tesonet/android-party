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
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.DataRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

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
        loginUseCase =
            LoginUseCase(
                dataRepository,
                backgroundScheduler,
                foregroundScheduler
            )
    }

    @Test(expected = IllegalArgumentException::class)
    fun loginThrowsException() {
        loginUseCase.buildUseCaseSingle().test()
    }

    @Test
    fun loginCompletes() {
        stubDataRepositoryLogin(Single.just(DomainTestDataFactory.createLoggedInUserEntity()))
        val testObserver = loginUseCase.buildUseCaseSingle(
            LoginUseCase.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun loginReturnsData() {
        val loggedInUserEntity = DomainTestDataFactory.createLoggedInUserEntity()
        stubDataRepositoryLogin(Single.just(loggedInUserEntity))
        val testObserver = loginUseCase.buildUseCaseSingle(
            LoginUseCase.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertValue(loggedInUserEntity)
    }

    @Test
    fun loginCallsRepository() {
        val username = DomainTestDataFactory.username
        val password = DomainTestDataFactory.password
        stubDataRepositoryLogin(Single.just(DomainTestDataFactory.createLoggedInUserEntity()))
        loginUseCase.buildUseCaseSingle(LoginUseCase.Params(username, password)).test()
        verify(dataRepository).login(username, password)
    }


    private fun stubDataRepositoryLogin(single: Single<LoggedInUserEntity>) {
        Mockito.`when`(dataRepository.login(anyString(), anyString()))
            .thenReturn(single)
    }

}