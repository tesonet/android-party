package com.example.domainLogin.data.datasource.remote

import app.cash.turbine.test
import com.example.domainLogin.data.dto.LoginDto
import com.example.domainLogin.data.service.LoginService
import com.example.domainLogin.utils.TestData
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test

class RemoteDataSourceImplTest : TestCase() {
    private val service: LoginService = mockk()
    private val remoteDataSource = RemoteDataSourceImpl(service)

    @Test
    fun `test getToken with valid output`() = kotlinx.coroutines.test.runTest {
        val provided = TestData.getTokenDto()
        val input = LoginDto(userName = "test_user", password = "test")
        coEvery {
            service.getToken(any())
        } returns provided

        remoteDataSource.fetchToken(input).test {
            assertEquals(provided, awaitItem())
            awaitComplete()
        }
    }
}