package com.example.domainServer.data.datasource.local

import app.cash.turbine.test
import com.example.domainServer.data.db.dao.ServerDao
import com.example.domainServer.data.db.entity.ServerEntity
import com.example.domainServer.utils.TestDataProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ServerLocalDataSourceImplTest : TestCase() {
    private val dao: ServerDao = mockk()
    private val dataSource = ServerLocalDataSourceImpl(dao)

    @Test
    fun `test getLocalServerList return valid output`() = runTest {
        val provided = TestDataProvider.getServerEntityList()
        coEvery {
            dao.fetchAllServers()
        } returns flow { emit(provided) }

        dataSource.getLocalServerList().test {
            assertEquals(provided, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test getLocalServerList return empty output`() = runTest {
        val provided = emptyList<ServerEntity>()
        coEvery {
            dao.fetchAllServers()
        } returns flow { emit(provided) }

        dataSource.getLocalServerList().test {
            assertEquals(provided, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test saveServerList return valid output`() {
        val provided = listOf<Long>(1, 3, 4)
        val input = TestDataProvider.getServerEntityList()
        every {
            dao.insertServers(any())
        } returns provided
        val got = dataSource.saveServerList(input)
        assertEquals(provided, got)
    }

    @Test
    fun `test saveServerList return empty output`() {
        val provided = emptyList<Long>()
        val input = emptyList<ServerEntity>()
        every {
            dao.insertServers(any())
        } returns provided
        val got = dataSource.saveServerList(input)
        assertEquals(provided, got)
    }
}
