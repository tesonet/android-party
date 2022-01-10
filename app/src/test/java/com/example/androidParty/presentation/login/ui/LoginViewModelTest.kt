package com.example.androidParty.presentation.login.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidParty.authentication.GetTokenUseCase
import com.example.androidParty.datalayer.repository.RemoteDataGenerator
import com.example.androidParty.presentation.login.domain.usecase.LoginUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private val loginUseCase = mock<LoginUseCase>()
    private val getToken = mock<GetTokenUseCase>()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var loginViewModel: LoginViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        beforeMock()
        loginViewModel = LoginViewModel(loginUseCase, getToken)
    }

    @Test
    fun login() {
        runBlockingTest {
            //given
            val userFlow = RemoteDataGenerator.getRemoteDataResponse()
            whenever(loginUseCase(any())).thenReturn(userFlow)

            loginViewModel.login("Mai", "123")

            val result = loginViewModel.loginResponse.getOrAwaitValue()

            //then
            Assert.assertEquals(result.data?.token, "token")
        }
    }

    private fun beforeMock() {
        runBlocking {
            val token = flow {
                emit("token")
            }
            whenever(getToken()).thenReturn(token)
            val tokenMock = token.first()
            println(tokenMock)
        }
    }
}