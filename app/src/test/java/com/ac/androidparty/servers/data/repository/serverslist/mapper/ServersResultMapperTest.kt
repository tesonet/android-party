package com.ac.androidparty.servers.data.repository.serverslist.mapper

import com.ac.androidparty.servers.data.remote.ServerResponse
import com.ac.androidparty.servers.data.repository.serverslist.ServersResult
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

internal class ServersResultMapperTest {

    private val throwable: Throwable = mock()

    private val servers = listOf(
        ServerResponse("test1", 1),
        ServerResponse("test2", 2),
        ServerResponse("test3", 3),
        ServerResponse("test4", 4),
        ServerResponse("test5", 5)
    )

    @Test
    fun `should map success if servers were returned`() {
        ServersResultMapper(servers) shouldBe ServersResult.Success(servers)
    }
}