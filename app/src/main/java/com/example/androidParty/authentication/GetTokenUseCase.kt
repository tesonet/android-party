package com.example.androidParty.authentication

import com.example.androidParty.presentation.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): Flow<String?> {
        return loginRepository.getAccessToken()
    }
}