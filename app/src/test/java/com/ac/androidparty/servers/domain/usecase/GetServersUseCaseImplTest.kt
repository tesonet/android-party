package com.ac.androidparty.servers.domain.usecase

import com.ac.androidparty.servers.data.remote.ServerResponse
import com.ac.androidparty.servers.data.repository.cachedserverslist.CachedServersListRepository
import com.ac.androidparty.servers.data.repository.serverslist.ServersListRepository
import com.ac.androidparty.servers.data.repository.serverslist.ServersResult
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class GetServersUseCaseImplTest {
    private val serversListRepository: ServersListRepository = mock()
    private val cachedServersListRepository: CachedServersListRepository = mock()

    private val getServersUseCaseImpl: GetServersUseCase =
        GetServersUseCaseImpl(serversListRepository, cachedServersListRepository)

    private val servers = listOf(
        ServerResponse("test1", 1),
        ServerResponse("test2", 2),
        ServerResponse("test3", 3),
        ServerResponse("test4", 4),
        ServerResponse("test5", 5)
    )

    @Test
    fun `should put servers in cache when call is successful`() {
        runBlocking {
            whenever(serversListRepository.getServers()) doReturn ServersResult.Success(servers)

            getServersUseCaseImpl.getServers() shouldBe ServersResult.Success(servers)
            verify(cachedServersListRepository).putServersList(servers)
        }
    }

    @Test
    fun `should get servers from cache when call is unsuccessful`() {
        runBlocking {
            whenever(cachedServersListRepository.getServersList()) doReturn servers
            whenever(serversListRepository.getServers()) doReturn ServersResult.Error(servers)

            getServersUseCaseImpl.getServers() shouldBe ServersResult.Error(servers)
            verify(cachedServersListRepository).getServersList()
        }
    }
}