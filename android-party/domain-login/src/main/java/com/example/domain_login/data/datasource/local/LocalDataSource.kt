package com.example.domain_login.data.datasource.local

interface LocalDataSource {
    fun saveToken(token: String)
}