package com.czech.androidparty.responseStates

import com.czech.androidparty.models.LoginResponse

sealed class LoginState {
    data class Success(val data: LoginResponse?): LoginState()
    data class Error(val message: String): LoginState()
    object Loading: LoginState()
}
