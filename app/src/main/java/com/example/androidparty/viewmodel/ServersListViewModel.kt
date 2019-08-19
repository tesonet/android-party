package com.example.androidparty.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.androidparty.repository.Repository

class ServersListViewModel(val repository: Repository) {
    val mServersList = MutableLiveData<List<ServerViewModel>>()

    fun getServersList(){

    }
}