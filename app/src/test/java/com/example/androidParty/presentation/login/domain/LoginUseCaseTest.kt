package com.example.androidParty.presentation.login.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidParty.datalayer.network.LoginCredentials
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.datalayer.repository.RemoteDataGenerator
import com.example.androidParty.presentation.login.LoginRepository
import com.example.androidParty.presentation.login.domain.entity.User
import com.example.androidParty.presentation.login.domain.usecase.LoginUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    private val loginRepository = mock(LoginRepository::class.java)

    private val loginUseCase = LoginUseCase(loginRepository)

    @Test
    fun `return token when login sucss`() {
        runBlocking {
            //given
            val paramObject = mock(JSONObject()::class.java)
            paramObject.put("username", "Mai")
            paramObject.put("password", "124")
            val body = LoginCredentials("username","password")
            //val mockUser = RemoteDataGenerator.getRemoteDataResponse()
            val mocked = flowOf(Resource.Success(User("token")))
            whenever(loginRepository.login(body)).thenReturn(mocked)
            var token = ""

            //when
            loginUseCase(body).collect {
                if (it.data != null) {
                    token = it.data!!.token
                }
            }

            //then
            Assert.assertEquals(token, "token")
        }
    }

    @Test
    fun `return error message when login failed`() {
        runBlocking {
            //given
            val paramObject = mock(JSONObject()::class.java)
            paramObject.put("username", "Mai")
            paramObject.put("password", "124")
           // val body: RequestBody =
            //    RequestBody.create(MediaType.parse("application/json"), paramObject.toString())
            val body = LoginCredentials("username","password")
            val mockUser = RemoteDataGenerator.getRemoteDataErrorResponse()
            whenever(loginRepository.login(body)).thenReturn(mockUser)
            var errorMessage = ""

            //when
            loginUseCase(body).collect {
                if (it.message != null) {
                    errorMessage = it.message!!
                }
            }

            //then
            Assert.assertEquals(errorMessage, "Error Happened")
        }
    }

}
