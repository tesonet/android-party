package com.thescriptan.tesonetparty.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thescriptan.tesonetparty.login.model.LoginRequest
import com.thescriptan.tesonetparty.login.repository.LoginRepository
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import com.thescriptan.tesonetparty.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    init {
        isLoggedIn()
    }

    fun navigateToList() {
        navigator.navigateTo(Screen.LIST)
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = LoginState.Loading
            when (val loginResult = repository.login(loginRequest)) {
                is Result.Error -> _loginState.value =
                    LoginState.Error("Error: ${loginResult.message}")
                is Result.Success -> {
                    delay(2000L)
                    _loginState.value = LoginState.Authorized
                }
                else -> _loginState.value = LoginState.Idle
            }
        }
    }

    private fun isLoggedIn() = viewModelScope.launch {
        val test = repository.isLoggedIn()
        println(test)
        if (test)
            _loginState.value = LoginState.Authorized
    }
}