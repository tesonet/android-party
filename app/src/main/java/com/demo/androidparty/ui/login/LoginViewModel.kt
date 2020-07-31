package com.demo.androidparty.ui.login

import androidx.lifecycle.viewModelScope
import com.demo.androidparty.base.BaseViewModel
import com.demo.androidparty.dto.LoginData
import com.demo.androidparty.utils.InternetStateProvider
import com.demo.androidparty.utils.NetworkResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val model: LoginModel,
    private val internetStateProvider: InternetStateProvider
) : BaseViewModel() {

    internal val loggedIn = createSingleLiveEvent<Unit>()
    internal val error = createSingleLiveEvent<String>()

    internal fun login(username: String?, password: String?) = viewModelScope.launch {
        if (!internetStateProvider.isOnline()) {
            error.setValue("Please check your internet connection")
            return@launch
        }
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            error.setValue("Please enter username and password")
            return@launch
        }

        val loginData = LoginData(username, password)
        when (val response = model.login(loginData)) {
            is NetworkResult.Success -> {
                model.save(response.data.token)
                loggedIn.call()
            }
            is NetworkResult.Error ->
                error.setValue("Something is wrong. Response code = ${response.errorCode}")
        }
    }
}