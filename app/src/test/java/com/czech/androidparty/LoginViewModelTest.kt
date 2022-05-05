package com.czech.androidparty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse
import com.czech.androidparty.preferences.SharedPrefs
import com.czech.androidparty.repositories.ListRepository
import com.czech.androidparty.repositories.LoginRepository
import com.czech.androidparty.responseStates.LoginState
import com.czech.androidparty.ui.login.LoginViewModel
import com.czech.androidparty.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get: Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get: Rule
    val testCoroutineRule = TestRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var sharedPrefs: SharedPrefs

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun initMocks(){
        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun testLogin() = testCoroutineDispatcher.runBlockingTest {
        val loginRequest = LoginRequest(username = "", password = "")

        val loginResponse = LoginResponse(token = "")

        val loginViewModel = LoginViewModel(loginRepository, sharedPrefs)

        val response = DataState.data(loginResponse.toString(), loginResponse)

        val channel = Channel<DataState<LoginResponse>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(loginRepository.execute(loginRequest)).thenReturn(flow)

        launch {
            channel.send(response)
        }

        loginViewModel.login(loginRequest.username, loginRequest.password)

        Assert.assertEquals(true, loginViewModel.loginState.value == LoginState.Success(loginResponse))
        Assert.assertEquals(false, loginViewModel.loginState.value == LoginState.Loading)
        Assert.assertEquals(false, loginViewModel.loginState.value == LoginState.Error(""))
    }
}