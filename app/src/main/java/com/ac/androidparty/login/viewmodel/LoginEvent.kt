package com.ac.androidparty.login.viewmodel

internal interface LoginEvent {
    data class Login(val navigateToServers: () -> Unit) : LoginEvent
    data class UsernameChanged(val username: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
}