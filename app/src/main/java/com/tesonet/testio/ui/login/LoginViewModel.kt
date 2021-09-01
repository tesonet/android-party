package com.tesonet.testio.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tesonet.testio.managers.ServersManager
import com.tesonet.testio.services.data.user.RequestUser
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyFields
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyName
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyPassword
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.FulfilledLogin
import com.tesonet.testio.utils.Resource
import com.tesonet.testio.utils.SingleEventLiveData

class LoginViewModel(private val serversManager: ServersManager) : ViewModel() {

    private val _uiLoginEvent = SingleEventLiveData<UiEventLogin>()
    val uiLoginEvent: LiveData<UiEventLogin>
        get() = _uiLoginEvent

    val requestToken: LiveData<Resource<String>>
        get() = serversManager.requestToken

    fun getAccessToken(requestUser: RequestUser) {
        serversManager.getAccessToken(requestUser)
    }

    fun logIn(username: String?, password: String?) {
        when {
            username.isNullOrEmpty() && password.isNullOrEmpty() -> _uiLoginEvent.value = EmptyFields
            username.isNullOrEmpty() -> _uiLoginEvent.value = EmptyName
            password.isNullOrEmpty() -> _uiLoginEvent.value = EmptyPassword
            else -> _uiLoginEvent.value = FulfilledLogin(RequestUser(username, password))
        }
    }

    sealed class UiEventLogin {
        object EmptyFields : UiEventLogin()
        object EmptyName : UiEventLogin()
        object EmptyPassword : UiEventLogin()
        class FulfilledLogin(val requestUser: RequestUser) : UiEventLogin()
    }
}