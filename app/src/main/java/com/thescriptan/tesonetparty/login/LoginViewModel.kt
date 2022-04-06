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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _loadingVisibility = MutableStateFlow(false)
    val loadingVisibility: StateFlow<Boolean> = _loadingVisibility

    private val _idleVisibility = MutableStateFlow(false)
    val idleVisibility: StateFlow<Boolean> = _idleVisibility

    private val _errorMessage = MutableSharedFlow<String>(replay = 1)
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        isLoggedIn()
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = LoginState.Loading
            delay(1500L)
            when (val loginResult = repository.login(loginRequest)) {
                is Result.Error -> {
                    val errorMessage = "${loginResult.message}"
                    _loginState.value = LoginState.Error
                    _errorMessage.tryEmit(errorMessage)
                }
                is Result.Success -> {
                    _loginState.value = LoginState.Authorized
                }
                else -> _loginState.value = LoginState.Idle
            }
        }
    }

    fun handleVisibility(loginState: LoginState) {
        when (loginState) {
            LoginState.Idle -> {
                _idleVisibility.value = true
                _loadingVisibility.value = false
            }
            LoginState.Loading -> {
                _idleVisibility.value = false
                _loadingVisibility.value = true
            }
            LoginState.Authorized -> navigateToList()
            is LoginState.Error -> {
                _idleVisibility.value = true
                _loadingVisibility.value = false
            }
        }
    }

    private fun navigateToList() {
        navigator.navigateTo(Screen.LIST)
    }

    private fun isLoggedIn() = viewModelScope.launch {
        val test = repository.isLoggedIn()
        println(test)
        if (test)
            _loginState.value = LoginState.Authorized
    }
}