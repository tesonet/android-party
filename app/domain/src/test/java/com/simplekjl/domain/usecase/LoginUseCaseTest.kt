package com.simplekjl.domain.usecase

import app.simplekjl.test.util.MainCoroutineRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.Login
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoginUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var serversRepository: ServersRepository

    @MockK
    private lateinit var sessionManager: SessionManager

    private lateinit var userCase: LoginUseCase
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userCase = LoginUseCase(mainCoroutineRule.testDispatcher, serversRepository, sessionManager)
    }

    @Test
    fun `verify repository and session are called when login`() {
        val loginData: Login = fixture()
        val token = "token"
        coEvery { sessionManager.saveAuthToken(token) } returns Unit
        coEvery { serversRepository.login(loginData) } returns Result.Success(token)
        runBlockingTest {
            val result = userCase(loginData)
            assert((result as Result.Success).data == token)
        }
        verify(atMost = 1) { sessionManager.saveAuthToken(token) }
        coVerify(atMost = 1) { serversRepository.login(loginData) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
