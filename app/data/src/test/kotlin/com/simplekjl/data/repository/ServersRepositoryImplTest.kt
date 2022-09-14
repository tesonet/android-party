package com.simplekjl.data.repository

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.client.ClientService
import com.simplekjl.data.model.LoginRaw
import com.simplekjl.domain.model.Login
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class ServersRepositoryImplTest {

    @MockK
    private lateinit var networkSource: NetworkSource

    @MockK
    private lateinit var client: ClientService

    private lateinit var repository: ServersRepository

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = ServersRepositoryImpl(networkSource)
        coEvery { networkSource.getClient() } returns client
    }

    @Test
    fun `when login return token`() {
        val login = Login("lol", "lol")
        runBlockingTest {
            coEvery {
                networkSource.getClient().login(LoginRaw(login.username, login.password))
            } returns Response.success(fixture())

            val result = repository.login(login)
            val token = (result as Result.Success).data
            assert(token.isNotEmpty())
        }
    }

    @Test
    fun `when login fails  return error`() {
        val login = Login("lol", "lol")
        runBlockingTest {
            coEvery {
                networkSource.getClient().login(LoginRaw(login.username, login.password))
            } returns Response.error(400, fixture())

            val result = repository.login(login)
            assertTrue { result is Result.Error }
        }
    }

    @Test
    fun `when getServers return listOf(ServerDetailsRaw)`() {
        runBlockingTest {
            coEvery {
                networkSource.getClient().getServers()
            } returns Response.success(fixture())

            val result = repository.getAllServers()
            val servers = (result as Result.Success).data
            assert(servers.isNotEmpty())
        }
    }

    @Test
    fun `when getServers fail then return Error`() {
        runBlockingTest {
            coEvery {
                networkSource.getClient().getServers()
            } returns Response.error(400, fixture())

            val result = repository.getAllServers()
            assertTrue { result is Result.Error }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
