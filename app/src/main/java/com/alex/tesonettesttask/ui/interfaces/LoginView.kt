package com.alex.tesonettesttask.ui.interfaces

interface LoginView {
    fun onError(message: String)
    fun onLogin()
    fun onServersFetched()
}