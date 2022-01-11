package com.example.androidParty.datalayer.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidParty.datalayer.MainCoroutineRules
import com.example.androidParty.datalayer.network.LoginCredentials
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class LoginRepositoryImpTest {

    private val loginRepository = MyFakeRepository()

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mainCoroutineRule = MainCoroutineRules()

    @Test
    fun login() = runBlocking {
        //given
        val paramObject = mock(JSONObject()::class.java)
        paramObject.put("username", "Mai")
        paramObject.put("password", "124")
//        val body: RequestBody =
//            RequestBody.create(MediaType.parse("application/json"), paramObject.toString())

        val body = LoginCredentials("username", "password")
        //when
        val token = loginRepository.login(body).first()

        //then
        Assert.assertEquals(token.data?.token, "token")
    }

    @Test
    fun getAccessToken() = runBlocking {
        //given

        //when
        val token = loginRepository.getAccessToken().first()

        //then
        Assert.assertEquals(token, "token")
    }

}