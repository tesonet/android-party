package com.example.domainLogin.domain.usecase

import com.example.core.usecase.BaseUseCase
import com.example.domainLogin.domain.model.Token

interface LoginUseCase : BaseUseCase<LoginUseCase.Input, LoginUseCase.Output> {
    data class Input(
        val userName: String,
        val password: String
    ) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        data class Success(val token: Token) : Output()
        data class UnknownError(val message: String) : Output()
        object NetworkError : Output()
    }
}
