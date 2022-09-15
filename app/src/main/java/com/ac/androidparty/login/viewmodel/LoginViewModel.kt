package com.ac.androidparty.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.androidparty.login.data.repository.LoginRepository
import com.ac.androidparty.login.data.repository.LoginResult
import com.ac.androidparty.login.domain.model.Login
import com.ac.androidparty.login.presentation.LoginState
import com.ac.androidparty.login.presentation.LoginStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private var login = Login("", "")

    private val viewStateMapper: LoginStateMapper = LoginStateMapper

    private val _state = MutableStateFlow<LoginState>(LoginState.Initial)

    val state: StateFlow<LoginState>
        get() = _state

    fun updateUsername(username: String) {
        login = login.copy(username = username)
        _state.value = LoginState.Initial
    }

    fun updatePassword(password: String) {
        login = login.copy(password = password)
        _state.value = LoginState.Initial
    }

    fun login() {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            flowOf(
                loginRepository.login(
                    login = login
                )
            ).collect(::applyLoginResult)
        }
    }

    private fun applyLoginResult(loginResult: LoginResult) = viewStateMapper(loginResult).run {
        _state.value = this
        Log.d("lmao", this.toString())
    }
}