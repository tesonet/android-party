package com.tesonet.testio.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.dao.ServerDao
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.local.entity.Token
import com.tesonet.testio.data.mapper.ServerMapper
import com.tesonet.testio.data.remote.PlaygroundApi
import com.tesonet.testio.util.asynclauncher.BlockingAsyncLauncher
import com.tesonet.testio.util.networkavailability.NetworkAvailableAvailability
import com.tesonet.testio.util.networkavailability.NetworkNotAvailableAvailability
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@Suppress("DeferredResultUnused")
class ServerRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val sampleToken = Token("123456789")
    private val sampleServers = listOf(
        Server(0, "Lithuania", "1234"),
        Server(1, "Latvia", "4321"),
        Server(2, "Estonia", "54321")
    )
    private val apiSampleServers = sampleServers.map { ServerMapper.map(it) }
    private val sampleException = RuntimeException("This is my test exception")

    private val api = mock<PlaygroundApi> {
        on { getServers(any()) } doReturn CompletableDeferred(apiSampleServers)
    }
    private val errorApi = mock<PlaygroundApi> {
        on { getServers(any()) } doThrow sampleException
    }

    private val serverDao = mock<ServerDao> {
        on { selectAll() } doReturn sampleServers
    }

    private val networkAvailableRepository = ServerRepository(api, serverDao, NetworkAvailableAvailability(), BlockingAsyncLauncher())
    private val networkNotAvailableRepository = ServerRepository(api, serverDao, NetworkNotAvailableAvailability(), BlockingAsyncLauncher())

    @Test
    fun getServers_networkAvailable_success() {
        val resource = networkAvailableRepository.getServers(sampleToken).value

        assertEquals(Resource.Status.SUCCESS, resource?.status)
        assertEquals(sampleServers, resource?.data)

        verify(api).getServers(any())
        verify(serverDao).deleteAll()
        verify(serverDao).insertMany(any())
    }

    @Test
    fun getServers_networkAvailable_apiError() {
        val serverRepository = ServerRepository(errorApi, serverDao, NetworkAvailableAvailability(), BlockingAsyncLauncher())
        val resource = serverRepository.getServers(sampleToken).value

        assertEquals(Resource.Status.ERROR, resource?.status)
        assertEquals(sampleException, resource?.exception)

        verify(errorApi).getServers(any())
        verifyZeroInteractions(serverDao)
    }

    @Test
    fun getServers_networkAvailable_caching() {
        networkAvailableRepository.getServers(sampleToken)
        networkAvailableRepository.getServers(sampleToken)
        verify(api, times(1)).getServers(any())
    }

    @Test
    fun getServers_networkNotAvailable() {
        val resource = networkNotAvailableRepository.getServers(sampleToken).value
        assertEquals(sampleServers, resource?.data)
        verifyZeroInteractions(api)
    }

    @Test
    fun getServersFromLocalDb() {
        val resource = networkNotAvailableRepository.getServers(sampleToken).value
        assertEquals(sampleServers, resource?.data)
        verifyZeroInteractions(api)
    }

    @Test
    fun deleteAllFromLocalDb() {
        networkAvailableRepository.getServers(sampleToken)
        networkAvailableRepository.deleteAllFromLocalDb()
        networkAvailableRepository.getServers(sampleToken)

        // Verify that API is called 2 times: for initial fetch and after deletion
        // If entries aren't deleted API is called only 1 time because of caching
        verify(api, times(2)).getServers(any())
    }
}