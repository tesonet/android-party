package com.example.domainLogin.data.repository

import app.cash.turbine.test
import com.example.domainLogin.data.datasource.local.LocalDataSource
import com.example.domainLogin.data.datasource.remote.RemoteDataSource
import com.example.domainLogin.domain.model.LoginInfo
import com.example.domainLogin.utils.TestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginRepositoryImplTest : TestCase() {
    private val localSource: LocalDataSource = mockk()
    private val remoteSource: RemoteDataSource = mockk()
    private val repository = LoginRepositoryImpl(remoteSource, localSource)

    @Test
    fun `test fetchToken return valid output`() = runTest {
        val provided = TestData.getTokenDto()
        val expected = TestData.getToken()
        val input = LoginInfo(userName = "test", password = "test")
        coEvery {
            remoteSource.fetchToken(any())
        } returns flow { emit(provided) }

        repository.fetchToken(input).test {
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test saveToken verify localSource call`() {
        val input = TestData.getToken()
        every {
            localSource.saveToken(any())
        } returns Unit
        repository.saveToken(input)
        verify(exactly = 1) { localSource.saveToken(input.token) }
    }
}