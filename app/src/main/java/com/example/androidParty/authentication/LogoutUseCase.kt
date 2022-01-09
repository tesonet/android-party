package com.example.androidParty.authentication

import com.example.androidParty.presentation.login.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() {
        return loginRepository.logout()
    }
}