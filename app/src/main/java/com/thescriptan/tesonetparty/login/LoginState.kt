package com.thescriptan.tesonetparty.login

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Authorized : LoginState()
    object Error : LoginState()
}
