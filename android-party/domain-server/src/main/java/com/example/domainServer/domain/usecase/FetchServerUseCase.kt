package com.example.domainServer.domain.usecase

import com.example.core.usecase.BaseUseCase
import com.example.domainServer.domain.model.Server

interface FetchServerUseCase : BaseUseCase<FetchServerUseCase.Input, FetchServerUseCase.Output> {
    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        data class Success(val servers: List<Server>) : Output()
        data class UnknownError(val message: String) : Output()
        object NetworkError : Output()
    }
}
