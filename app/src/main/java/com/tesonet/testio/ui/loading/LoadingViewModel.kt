package com.tesonet.testio.ui.loading

import androidx.lifecycle.ViewModel
import com.tesonet.testio.managers.ServersManager

class LoadingViewModel(private val serversManager: ServersManager) : ViewModel() {

    fun getServers(token: String) {
        serversManager.fetchServers(token)
    }
}