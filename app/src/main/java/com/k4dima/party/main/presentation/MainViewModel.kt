package com.k4dima.party.main.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k4dima.party.main.data.model.Server
import com.k4dima.party.main.domain.ServersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject
constructor(private val useCase: ServersUseCase) : ViewModel() {
    //val adapter = ServersAdapter()
    val failure = MutableLiveData<String>()
    val servers = object : MutableLiveData<List<Server>>() {
        override fun onActive() {
            if (value == null)
                viewModelScope.launch {
                    try {
                        postValue(useCase.data(Unit))
                    } catch (e: Exception) {
                        failure.postValue(e.localizedMessage)
                    }
                }
        }
    }
    fun logout() {
        useCase.logout()
        //servers.value = null
        failure.value = "logout"
    }
}