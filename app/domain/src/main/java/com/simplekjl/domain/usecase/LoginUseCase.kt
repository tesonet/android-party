package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.Login
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.domain.utils.Result
import com.simplekjl.domain.utils.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: ServersRepository,
    private val sessionManager: SessionManager
) :
    SuspendUseCase<Login, Result<String>>(dispatcher) {
    override suspend fun execute(parameters: Login): Result<String> {
        return when (val result = repository.login(parameters)) {
            is Result.Error -> Result.Error(Throwable("Something went wrong"))
            is Result.Success -> {
                if (result.data.isNotEmpty()) {
                    sessionManager.saveAuthToken(result.data)
                    Result.Success(result.data)
                } else {
                    Result.Error(Throwable("Invalid token"))
                }
            }
        }
    }
}
