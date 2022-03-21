package com.example.domainServer.domain.usecase

import com.example.core.dispatcher.BaseDispatcherProvider

import com.example.domainServer.domain.repository.ServerRepository
import com.example.domainServer.utils.TestDataProvider
import com.example.domainServer.utils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import java.io.IOException

class FetchServerUseCaseImplTest : TestCase() {
    private val repository: ServerRepository = mockk()
    private val testDispatcher: BaseDispatcherProvider = TestDispatcherProvider()
    private val useCase = FetchServerUseCaseImpl(repository, testDispatcher)

    @Test
    fun `test fetchServerUseCase with input return valid output`() = runBlocking {
        val exceptedList = TestDataProvider.getServerList()
        coEvery {
            repository.fetchServer()
        } returns flow { emit(exceptedList) }

        useCase.execute(FetchServerUseCase.Input).collect {
            assert(it is FetchServerUseCase.Output.Success)
            MatcherAssert.assertThat(
                (it as FetchServerUseCase.Output.Success).servers.size,
                CoreMatchers.equalTo(3)
            )
            MatcherAssert.assertThat(
                it.servers,
                CoreMatchers.equalTo(exceptedList)
            )
        }
    }

    @Test
    fun `test fetchServerUseCase with input return network exception`() = runBlocking {
        coEvery {
            repository.fetchServer()
        } returns flow { emit(throw IOException()) }

        useCase.execute(FetchServerUseCase.Input).collect {
            assert(it is FetchServerUseCase.Output.NetworkError)
        }
    }

    @Test
    fun `test fetchServerUseCase with input return unknown exception`() = runBlocking {
        coEvery {
            repository.fetchServer()
        } returns flow { emit(throw NullPointerException()) }

        useCase.execute(FetchServerUseCase.Input).collect {
            assert(it is FetchServerUseCase.Output.UnknownError)
        }
    }
}
