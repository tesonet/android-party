package com.example.domainServer.data.repository

import app.cash.turbine.test
import com.example.domainServer.data.datasource.local.ServerLocalDataSource
import com.example.domainServer.data.datasource.remote.ServerRemoteDataSource
import com.example.domainServer.domain.model.Server
import com.example.domainServer.utils.TestDataProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ServerRepositoryImplTest : TestCase() {
    private val localSource = mockk<ServerLocalDataSource>()
    private val remoteSource = mockk<ServerRemoteDataSource>()
    private val repository = ServerRepositoryImpl(remoteSource, localSource)

    @Test
    fun `test fetchServer return valid data`() = runTest {
        val inputRemoteSource = TestDataProvider.getServerDtoList()
        val inputSaveLocalSource = listOf<Long>(1, 2, 3)
        val inputLocalSource = TestDataProvider.getServerEntityList()
        val expected = TestDataProvider.getServerList()
        coEvery {
            remoteSource.fetchServerList()
        } returns flow { emit(inputRemoteSource) }

        every {
            localSource.saveServerList(any())
        } returns inputSaveLocalSource

        coEvery {
            localSource.getLocalServerList()
        } returns flow { emit(inputLocalSource) }

        repository.fetchServer().test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test fetchServer return empty data`() = runTest {
        val inputRemoteSource = TestDataProvider.getServerDtoList()
        val inputSaveLocalSource = emptyList<Long>()
        val inputLocalSource = TestDataProvider.getServerEntityList()
        val expected = emptyList<Server>()
        coEvery {
            remoteSource.fetchServerList()
        } returns flow { emit(inputRemoteSource) }

        every {
            localSource.saveServerList(any())
        } returns inputSaveLocalSource

        coEvery {
            localSource.getLocalServerList()
        } returns flow { emit(inputLocalSource) }

        repository.fetchServer().test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }
}