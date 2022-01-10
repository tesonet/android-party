package com.example.androidParty.presentation.login

import com.example.androidParty.datalayer.LoginCredentials
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
  fun login(body: LoginCredentials): Flow<Resource<User>>
  suspend fun logout()
  suspend fun getAccessToken(): Flow<String?>
}