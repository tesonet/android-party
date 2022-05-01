package com.czech.androidparty.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.repositories.LoginRepository
import com.czech.androidparty.responseStates.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginState = MutableStateFlow<LoginState?>(null)

    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            loginRepository.execute(
                LoginRequest(
                    username = username,
                    password = password
                )
            ).collect {
                when {
                    it.isLoading -> {
                        loginState.value = LoginState.Loading
                    }
                    it.data?.token == null -> {
                        loginState.value = LoginState.Error(message = it.message!!)
                    }
                    else -> {
                        it.data.let { response ->
                            loginState.value = LoginState.Success(data = response)
                        }
                    }
                }
            }
        }
    }

}