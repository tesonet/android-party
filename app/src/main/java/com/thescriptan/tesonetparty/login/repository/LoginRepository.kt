package com.thescriptan.tesonetparty.login.repository

import com.thescriptan.tesonetparty.login.model.LoginRequest
import com.thescriptan.tesonetparty.login.model.LoginResponse
import com.thescriptan.tesonetparty.network.LoginApi
import com.thescriptan.tesonetparty.utils.Result
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}

@ActivityScoped
class LoginRepositoryImpl @Inject constructor(private val api: LoginApi) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        val response = try {
            api.login(loginRequest)
        } catch (e: Exception) {
            return Result.Error("Network error")
        }

        if (response.code() == 200) {
            response.body()?.let {
                return Result.Success(it)
            }
        } else if (response.code() == 401) {
            return Result.Error("Authentication error")
        }
        return Result.Error("Unknown error")
    }
}