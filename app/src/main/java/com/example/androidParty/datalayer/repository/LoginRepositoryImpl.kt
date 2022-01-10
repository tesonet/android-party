package com.example.androidParty.datalayer.repository

import com.example.androidParty.datalayer.network.LoginCredentials
import com.example.androidParty.datalayer.UserPreferences
import com.example.androidParty.datalayer.dto.toUser
import com.example.androidParty.datalayer.network.BaseDataSource
import com.example.androidParty.datalayer.network.NordApiService
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.LoginRepository
import com.example.androidParty.presentation.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
  private val preferences: UserPreferences,
  private val nordApiService: NordApiService
) : LoginRepository, BaseDataSource() {

  override fun login(body: LoginCredentials): Flow<Resource<User>> = flow {
    emit(Resource.Loading())
    val result = getResult { nordApiService.login(body) }
    if (result.data != null) {
      saveAccessToken(result.data.toUser().token)
      emit(Resource.Success(result.data.toUser()))
    } else {
      emit(Resource.Error<User>(result.message ?: "Error"))
    }
  }

  override suspend fun getAccessToken(): Flow<String?> {
    return preferences.accessToken
  }

  override suspend fun logout() {
    preferences.clear()
  }

  private suspend fun saveAccessToken(accessToken: String) {
    preferences.saveAccessTokens(accessToken)
  }
}