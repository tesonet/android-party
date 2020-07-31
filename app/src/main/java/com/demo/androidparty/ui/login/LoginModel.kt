package com.demo.androidparty.ui.login

import com.demo.androidparty.di.annotations.IO
import com.demo.androidparty.dto.LoginData
import com.demo.androidparty.dto.TokenData
import com.demo.androidparty.network.ApiService
import com.demo.androidparty.storage.preferences.AppPreferences
import com.demo.androidparty.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginModel(
    @IO private val dispatcher: CoroutineDispatcher,
    private val apiService: ApiService,
    private val prefs: AppPreferences
) {
    internal suspend fun login(data: LoginData): NetworkResult<TokenData> =
        withContext(dispatcher) {
            val response = apiService.login(data)
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.code())
            }
        }

    internal fun save(token: String) {
        prefs.saveToken(token)
    }
}