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

import com.baruckis.androidparty.domain.TestDataFactory
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

    @Mock
    lateinit var mainRepository: MainRepository

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
                mainRepository,
                backgroundScheduler,
                foregroundScheduler
            )
    }

    @Test
    fun sendAuthorizationCompletes() {
        stubSendAuthorization(Single.just(TestDataFactory.createLoggedInUserEntity()))
        val testObserver = loginUseCase.buildUseCaseSingle(
            LoginUseCase.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun sendAuthorizationReturnsData() {
        val loggedInUserEntity = TestDataFactory.createLoggedInUserEntity()
        stubSendAuthorization(Single.just(loggedInUserEntity))
        val testObserver = loginUseCase.buildUseCaseSingle(
            LoginUseCase.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertValue(loggedInUserEntity)
    }

    private fun stubSendAuthorization(single: Single<LoggedInUserEntity>) {
        Mockito.`when`(mainRepository.login(anyString(), anyString()))
            .thenReturn(single)
    }

}