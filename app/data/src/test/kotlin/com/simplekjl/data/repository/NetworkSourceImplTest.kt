package com.simplekjl.data.repository

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.client.ClientService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class NetworkSourceImplTest {

    @MockK
    private lateinit var clientService: ClientService

    private lateinit var networkSourceImpl: NetworkSourceImpl

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        networkSourceImpl = NetworkSourceImpl(clientService)
    }

    @Test
    fun `when networkRepositoryImpl fetch client, then client mock is used`() {
        coEvery { clientService.getServers() } returns Response.success(fixture())
        runBlockingTest {
            networkSourceImpl.getClient().getServers()
            coVerify(atMost = 1) { clientService.getServers() }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
