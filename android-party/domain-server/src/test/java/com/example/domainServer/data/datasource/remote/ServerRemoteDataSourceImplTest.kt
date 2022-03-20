package com.example.domainServer.data.datasource.remote

import app.cash.turbine.test
import com.example.domainServer.data.dto.ServerDto
import com.example.domainServer.data.service.ServerListService
import com.example.domainServer.utils.TestDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ServerRemoteDataSourceImplTest : TestCase() {
    private val service: ServerListService = mockk()
    private val remoteDataSource = ServerRemoteDataSourceImpl(service)

    @Test
    fun `test fetchServerList with valid output`() = runTest {
        val provided = TestDataProvider.getServerDtoList()
        coEvery {
            service.fetchServerList()
        } returns provided

        remoteDataSource.fetchServerList().test {
            assertEquals(provided, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test fetchServerList with empty output`() = runTest {
        val provided = emptyList<ServerDto>()
        coEvery {
            service.fetchServerList()
        } returns provided

        remoteDataSource.fetchServerList().test {
            assertEquals(provided, awaitItem())
            awaitComplete()
        }
    }
}