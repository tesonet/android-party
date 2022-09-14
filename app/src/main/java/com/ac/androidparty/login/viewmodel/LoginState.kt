package com.ac.androidparty.login.viewmodel

sealed interface LoginState {
    object Success : LoginState
    object LoggedOut : LoginState
    object WrongCredentials : LoginState
    object NetworkError : LoginState
}