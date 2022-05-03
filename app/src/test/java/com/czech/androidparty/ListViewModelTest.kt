package com.czech.androidparty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.czech.androidparty.models.DataList
import com.czech.androidparty.models.LoginResponse
import com.czech.androidparty.preferences.SharedPrefs
import com.czech.androidparty.repositories.ListRepository
import com.czech.androidparty.responseStates.ListState
import com.czech.androidparty.ui.list.ListViewModel
import com.czech.androidparty.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get: Rule
    val instantTaskExecutorRule: org.junit.rules.TestRule = InstantTaskExecutorRule()

    @get: Rule
    val testCoroutineRule = TestRule()

    @Mock
    private lateinit var listRepository: ListRepository

    @Mock
    private lateinit var sharedPrefs: SharedPrefs

    @Before
    fun initMocks(){
        MockitoAnnotations.initMocks(this)

    }

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Test
    fun testGetDataFromDB() = testCoroutineDispatcher.runBlockingTest {

        val listViewModel = ListViewModel(listRepository, sharedPrefs)

        val dataResponse = DataList(name = "", distance = 0)

        val response = DataState.data(dataResponse.toString(), listOf(dataResponse))

        val channel = Channel<DataState<List<DataList>>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(listRepository.getFromDatabase()).thenReturn(flow)

        val job = launch {
            channel.send(response)
        }
        Assert.assertEquals(true, listViewModel.listState.value == ListState.Success(listOf(dataResponse)))
        Assert.assertEquals(false, listViewModel.listState.value == ListState.Loading)
        job.cancel()
    }

    @Test
    fun testGetDataWithNetwork() = testCoroutineDispatcher.runBlockingTest {

        val listViewModel = ListViewModel(listRepository, sharedPrefs)

        val loginResponse = LoginResponse(token = "")

        val dataResponse = DataList(name = "", distance = 0)

        val response = DataState.data(dataResponse.toString(), listOf(dataResponse))

        val channel = Channel<DataState<List<DataList>>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(listRepository.getFromNetwork(loginResponse.token!!)).thenReturn(flow)

        launch {
            channel.send(response)
        }

        listViewModel.getDataWithNetwork()

        val job = launch {
            channel.send(response)
        }

        Assert.assertEquals(true, listViewModel.listState.value == ListState.Success(listOf(dataResponse)))
        Assert.assertEquals(false, listViewModel.listState.value == ListState.Loading)
        job.cancel()
    }

}