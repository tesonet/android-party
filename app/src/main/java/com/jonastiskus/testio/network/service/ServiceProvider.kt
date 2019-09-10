package com.jonastiskus.testio.network.service

import com.jonastiskus.testio.auth.AuthService
import com.jonastiskus.testio.repository.room.AppDatabase

interface ServiceProvider {

    fun getApiService() : ApiService
    fun getAuthService() : AuthService
    fun getAppDatabase() : AppDatabase

}