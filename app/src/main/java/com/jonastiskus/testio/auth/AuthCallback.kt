package com.jonastiskus.testio.auth

interface AuthCallback {
    fun onLoginSuccessfull()
    fun onLoginFailed()
}