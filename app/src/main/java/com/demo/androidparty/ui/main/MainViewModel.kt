package com.demo.androidparty.ui.main

import com.demo.androidparty.base.BaseViewModel

class MainViewModel(model: MainModel) : BaseViewModel() {

    internal val token = createMutableLiveData<String?>()

    init {
        val appToken = model.getToken()
        token.setValue(appToken)
    }
}