package com.amaximan.tesonet.viewModel

import android.arch.lifecycle.ViewModel
import com.amaximan.tesonet.other.networking.Repository

class MainVM : ViewModel() {
    val servers = Repository.instance.getServersLiveData()
}