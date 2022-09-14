package com.simplekjl.domain.usecase

import app.simplekjl.test.util.MainCoroutineRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetAllServersUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var serversRepository: ServersRepository

    private lateinit var userCase: GetAllServersUseCase
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userCase = GetAllServersUseCase(mainCoroutineRule.testDispatcher, serversRepository)
    }

    @Test
    fun `verify repository  are called when fetching services`() {
        val serversData: List<ServerDetails> = fixture()
        coEvery { serversRepository.getAllServers() } returns Result.Success(serversData)
        runBlockingTest {
            val result = userCase.invoke(Unit)
            assert((result as Result.Success).data.size == serversData.size)
            assert((result as Result.Success).data[0] == serversData[0])
        }
        coVerify(atMost = 1) { serversRepository.getAllServers() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
