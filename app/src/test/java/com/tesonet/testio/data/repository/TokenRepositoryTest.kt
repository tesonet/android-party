package com.tesonet.testio.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.dao.TokenDao
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.data.local.entity.Token
import com.tesonet.testio.data.mapper.CredentialsMapper
import com.tesonet.testio.data.mapper.TokenMapper
import com.tesonet.testio.data.remote.PlaygroundApi
import com.tesonet.testio.data.remote.entity.ApiToken
import com.tesonet.testio.util.asynclauncher.BlockingAsyncLauncher
import com.tesonet.testio.util.networkavailability.NetworkAvailableAvailability
import com.tesonet.testio.util.networkavailability.NetworkNotAvailableAvailability
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response

@Suppress("DeferredResultUnused")
class TokenRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val sampleToken = Token("123456789")
    private val sampleCredentials = Credentials("Name", "123456")

    private val api = mock<PlaygroundApi> {
        on { login(any()) } doReturn CompletableDeferred(TokenMapper.map(sampleToken))
    }
    private val errorApi = mock<PlaygroundApi> {
        on { login(any()) } doThrow HttpException(Response.error<ApiToken>(401, ResponseBody.create(null, "")))
    }

    private val tokenDao = mock<TokenDao> {
        on { select() } doReturn sampleToken
    }
    private val emptyTokenDao = mock<TokenDao>()

    private val tokenRepository = TokenRepository(api, tokenDao, NetworkAvailableAvailability(), BlockingAsyncLauncher())

    @Test
    fun getToken_fromRemote() {
        val resource = tokenRepository.getToken(sampleCredentials).value
        assertEquals(Resource.Status.SUCCESS, resource?.status)
        verify(api).login(CredentialsMapper.map(sampleCredentials))
    }

    @Test
    fun getToken_internetRequired() {
        val repository = TokenRepository(api, emptyTokenDao, NetworkNotAvailableAvailability(), BlockingAsyncLauncher())
        val resource = repository.getToken(sampleCredentials).value

        assertEquals(Resource.Status.ERROR, resource?.status)
        assertThat(resource?.exception, instanceOf(TokenRepository.InternetRequiredException::class.java))
    }

    @Test
    fun getToken_badCredentials() {
        val repository = TokenRepository(errorApi, emptyTokenDao, NetworkAvailableAvailability(), BlockingAsyncLauncher())
        val resource = repository.getToken(sampleCredentials).value

        assertEquals(Resource.Status.ERROR, resource?.status)
        assertThat(resource?.exception, instanceOf(TokenRepository.BadCredentialsException::class.java))
    }

    @Test
    fun deleteTokenFromLocalDb() {
        tokenRepository.getToken(sampleCredentials)
        tokenRepository.deleteTokenFromLocalDb()
        tokenRepository.getToken(sampleCredentials)

        // Verify that API is called 2 times: for initial fetch and after deletion
        // If entries aren't deleted, API is called only 1 time because of caching
        verify(api, times(2)).login(any())
    }
}