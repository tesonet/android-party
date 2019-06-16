package com.mariussavickas.android_party

import com.codecave.outmatch.shared.server.ApiService
import com.google.gson.JsonParser
import com.mariussavickas.android_party.persistance.*
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Matchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    class TestUserDao: UserDao {
        override fun insertUser(user: User) {}
        override fun clear() {}
    }

    class TestServerInfoDao: ServerInfoDao {
        override fun getServerInfoAll(): Flowable<List<ServerInfo>> {
            return Flowable.just(arrayListOf(ServerInfo("value1", 1)))
        }
        override fun insert(serverInfo: List<ServerInfo>) {}
        override fun clear() {}
    }

    // Hack for Kotlin non-nullable param stuff
    open fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Mock
    private lateinit var mockApiService: ApiService
    @Mock
    private lateinit var mockAppDatabase: AppDatabase

    private val JSON_PARAM = "token"
    private val JSON_PARAM_2 = "value2"
    private val JSON_PARAM_NAME = "name"
    private val JSON_PARAM_DISTANCE = "distance"

    private val JSON_PARAM_VALUE = "tokenValue"
    private val JSON_PARAM_VALUE_2 = "ok2"

    @Test
    fun repository_fetchAccessToken_returnsCorrectValue() {
        val responseJsonElement = JsonParser().parse("{ $JSON_PARAM:$JSON_PARAM_VALUE }" )
        `when`(mockApiService.post(anyString(), any(), anyString()))
            .thenReturn(Single.just(responseJsonElement))

        `when`(mockAppDatabase.userDao()).thenReturn(TestUserDao())

        val repository = Repository(mockApiService, mockAppDatabase)
        val user = repository.fetchAccessToken(User(JSON_PARAM_2, JSON_PARAM_VALUE_2, null)).blockingGet()

        assertEquals(user.token, JSON_PARAM_VALUE)
    }

    @Test
    fun repository_fetchServerList_returnsCorrectValue() {
        val responseJsonElement = JsonParser().parse("[{ $JSON_PARAM_NAME:$JSON_PARAM_VALUE, $JSON_PARAM_DISTANCE: 1 }]" )
        `when`(mockApiService.get(anyString(), anyString()))
            .thenReturn(Single.just(responseJsonElement))

        `when`(mockAppDatabase.serverInfoDao()).thenReturn(TestServerInfoDao())

        val repository = Repository(mockApiService, mockAppDatabase)
        val serverList = repository.fetchServerList(User(JSON_PARAM_2, JSON_PARAM_VALUE_2, JSON_PARAM_VALUE_2)).blockingGet()

        assertEquals(serverList.size, 1)
        val expectedList = arrayListOf(ServerInfo(JSON_PARAM_VALUE, 1))
        assertEquals(serverList, expectedList)
    }
}
