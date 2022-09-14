package com.simplekjl.domain.repository

interface SessionManager {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String
    fun deleteAuthToken()
}
