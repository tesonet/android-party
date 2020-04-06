package com.example.androidparty.ui.loading

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.androidparty.dao.ServerDao
import com.example.androidparty.db.ServerEntity
import com.example.androidparty.repository.ServerRepository
import io.reactivex.Observable
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetServersTest1 {
    @Mock
    lateinit var application: Application
    @Mock
    lateinit var serverDao: ServerDao

    lateinit var loadingViewModel: LoadingViewModel
    lateinit var repository: ServerRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loadingViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(LoadingViewModel::class.java)
        repository = ServerRepository(serverDao)
    }

    @Test
    fun callApiWhenThereIsInternet() {
        //Mock
        val tokenString = "token"
        val serversEmptyList = emptyList<ServerEntity>()
        Mockito.doReturn(Observable.just(serversEmptyList)).`when`(repository.getServersList(tokenString))
        //Act
        loadingViewModel.getServers(tokenString, true)

        //Assert
        Assert.assertEquals(
            Observable.just(serversEmptyList),
            loadingViewModel.getServersObservable()
        )
    }
}