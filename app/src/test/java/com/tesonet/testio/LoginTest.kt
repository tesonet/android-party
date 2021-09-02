package com.tesonet.testio

import com.tesonet.testio.services.client.ApiClient
import com.tesonet.testio.services.data.token.Token
import com.tesonet.testio.services.data.user.RequestUser
import io.reactivex.Single
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner.*

@RunWith(Silent::class)
class LoginTest {

    @Mock
    private lateinit var apiClient: ApiClient

    @Test
    fun testConnectionWithCorrectCredentials() {
        val correctUserRequestCredentials = CORRECT_USER_CREDENTIALS
        val token = Token(EXTRA_REQUESTED_USER_TOKEN)
        `when`(apiClient.requestUserToken(correctUserRequestCredentials)).thenReturn(Single.just(token))
    }

    @Test(expected = Exception::class)
    fun testConnectionWithInCorrectCredentials() {
        val incorrectUserRequestCredentials = INCORRECT_USER_CREDENTIALS
        `when`(apiClient.requestUserToken(incorrectUserRequestCredentials)).thenThrow(Exception())
    }

    companion object {
        private const val CORRECT_USER_NAME = "tesonet"
        private const val CORRECT_USER_PASSWORD = "partyanimal"
        private const val INCORRECT_USER_NAME = "hello"
        private const val INCORRECT_USER_PASSWORD = "world"
        private val CORRECT_USER_CREDENTIALS = RequestUser(CORRECT_USER_NAME, CORRECT_USER_PASSWORD)
        private val INCORRECT_USER_CREDENTIALS = RequestUser(INCORRECT_USER_NAME, INCORRECT_USER_PASSWORD)

        private const val EXTRA_REQUESTED_USER_TOKEN: String = "f9731b590611a5a9377fbd02f247fcdf"
    }
}