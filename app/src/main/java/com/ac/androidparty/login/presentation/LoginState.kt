package com.ac.androidparty.login.presentation

internal interface LoginState {
    object Initial : LoginState
    object Loading : LoginState
    object Error : LoginState
    object WrongCredentials : LoginState
    data class Success(val token: String) : LoginState
}