package com.yasserakbbach.androidparty.login.data.repository

import com.yasserakbbach.androidparty.login.data.remote.LoginApi
import com.yasserakbbach.androidparty.login.data.remote.LoginResponse
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import kotlin.test.assertTrue

class LoginRepositoryImplTest {

    private val loginApi: LoginApi = mockk()
    private val sessionRepository: SessionRepository = mockk()
    private val loginRepositoryImpl: LoginRepositoryImpl = LoginRepositoryImpl(loginApi, sessionRepository)

    @Test
    fun `given valid login credentials When login Then session is saved`() {

        val loginCredentials = Login(
            username = "yasser",
            password = "yasser",
        )

        coEvery { loginApi.login(any()) } returns LoginResponse(token = "my_token")
        coEvery { sessionRepository.saveSession(any()) } just Runs

        runBlocking {
            loginRepositoryImpl.login(loginCredentials)
        }

        coVerify(exactly = 1) { sessionRepository.saveSession(any()) }
    }

    @Test
    fun `given failing login API call When login Then session is not saved And failure is expected`() {
        val loginCredentials = Login(
            username = "",
            password = "",
        )

        coEvery { loginApi.login(any()) } throws IllegalArgumentException("API call failed")

        runBlocking {
            val session = loginRepositoryImpl.login(loginCredentials)
            assertTrue(session.isFailure)
        }

        coVerify(exactly = 0) { sessionRepository.saveSession(any()) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}