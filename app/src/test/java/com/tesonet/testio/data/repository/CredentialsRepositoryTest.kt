package com.tesonet.testio.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.dao.CredentialsDao
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.util.asynclauncher.BlockingAsyncLauncher
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations


class CredentialsRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val sampleCredentials = Credentials("admin", "123456")
    private val sampleException = RuntimeException("This is my test exception")

    private val credentialsDao = mock<CredentialsDao> {
        on { select() } doReturn sampleCredentials
    }

    private val errorCredentialsDao = mock<CredentialsDao> {
        on { select() } doThrow sampleException
    }

    private lateinit var credentialsRepository: CredentialsRepository
    private lateinit var errorCredentialsRepository: CredentialsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        credentialsRepository = CredentialsRepository(credentialsDao, BlockingAsyncLauncher())
        errorCredentialsRepository = CredentialsRepository(errorCredentialsDao, BlockingAsyncLauncher())
    }

    @Test
    fun getCredentials_success() {
        val resource = credentialsRepository.getCredentials().value
        assertEquals(Resource.Status.SUCCESS, resource?.status)
        assertEquals(sampleCredentials, resource?.data)
    }

    @Test
    fun getCredentials_error() {
        val resource = errorCredentialsRepository.getCredentials().value
        assertEquals(Resource.Status.ERROR, resource?.status)
        assertEquals(sampleException, resource?.exception)
    }

    @Test
    fun getCredentials_caching() {
        credentialsRepository.getCredentials()
        credentialsRepository.getCredentials()
        verify(credentialsDao, times(1)).select()
    }

    @Test
    fun saveCredentials() {
        credentialsRepository.saveCredentials(sampleCredentials)
        val resource = credentialsRepository.getCredentials().value
        assertEquals(sampleCredentials, resource?.data)
    }

    @Test
    fun deleteCredentials() {
        credentialsRepository.saveCredentials(Credentials("Name", "Pa22w0rd"))
        credentialsRepository.deleteCredentials()
        val resource = credentialsRepository.getCredentials().value
        assertEquals(sampleCredentials, resource?.data) // Verify that credentials is fetched from DAO since cache is deleted
    }
}