package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.utils.Result
import com.simplekjl.domain.utils.Result.Error
import com.simplekjl.domain.utils.Result.Success
import com.simplekjl.domain.utils.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetAllServersUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: ServersRepository
) :
    SuspendUseCase<Unit, Result<List<ServerDetails>>>(dispatcher) {
    override suspend fun execute(parameters: Unit): Result<List<ServerDetails>> {
        return when (val result = repository.getAllServers()) {
            is Error -> Error(Throwable("Something went wrong"))
            is Success -> Success(result.data.sortedBy { it.distance })
        }
    }
}
