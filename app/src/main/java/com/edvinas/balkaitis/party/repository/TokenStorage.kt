package com.edvinas.balkaitis.party.repository

interface TokenStorage {

    fun saveToken(token: String)

    fun getToken(): String

    fun removeToken()
}
