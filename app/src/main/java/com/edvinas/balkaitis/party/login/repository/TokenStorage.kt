package com.edvinas.balkaitis.party.login.repository

interface TokenStorage {
    fun saveToken(token: String)

    fun getToken(): String

    fun removeToken()
}
