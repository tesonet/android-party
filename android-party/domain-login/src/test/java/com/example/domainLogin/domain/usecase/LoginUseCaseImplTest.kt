package com.example.domainLogin.domain.usecase

import app.cash.turbine.test
import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.domainLogin.domain.repository.LoginRepository
import com.example.domainLogin.utils.TestData
import com.example.domainLogin.utils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import java.io.IOException

class LoginUseCaseImplTest : TestCase() {
    private val repository: LoginRepository = mockk()
    private val testDispatcher: BaseDispatcherProvider = TestDispatcherProvider()
    private val useCase = LoginUseCaseImpl(repository, testDispatcher)

    @Test
    fun `test loginUseCaseImpl with input return valid output`() = runBlocking {
        val excepted = TestData.getToken()
        coEvery {
            repository.fetchToken(any())
        } returns flow { emit(excepted) }
        every {
            repository.saveToken(any())
        } returns Unit

        useCase.execute(LoginUseCase.Input(userName = "test", password = "test")).test {
            val item = awaitItem()
            assert(item is LoginUseCase.Output.Success)
            MatcherAssert.assertThat(
                (item as LoginUseCase.Output.Success).token,
                CoreMatchers.equalTo(excepted)
            )
            awaitComplete()
        }
    }

    @Test
    fun `test loginUseCaseImpl with input return network error output`() = runBlocking {
        coEvery {
            repository.fetchToken(any())
        } returns flow { emit(throw IOException()) }
        every {
            repository.saveToken(any())
        } returns Unit

        useCase.execute(LoginUseCase.Input(userName = "test", password = "test")).test {
            val item = awaitItem()
            assert(item is LoginUseCase.Output.NetworkError)
            awaitComplete()
        }
    }

    @Test
    fun `test loginUseCaseImpl with input return unknown error output`() = runBlocking {
        coEvery {
            repository.fetchToken(any())
        } returns flow { emit(throw RuntimeException()) }
        every {
            repository.saveToken(any())
        } returns Unit

        useCase.execute(LoginUseCase.Input(userName = "test", password = "test")).test {
            val item = awaitItem()
            assert(item is LoginUseCase.Output.UnknownError)
            awaitComplete()
        }
    }
}
