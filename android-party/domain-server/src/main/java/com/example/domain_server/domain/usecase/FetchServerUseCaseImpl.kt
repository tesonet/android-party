package com.example.domain_server.domain.usecase

import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.core.ext.isNetworkException
import com.example.domain_server.domain.repository.ServerRepository
import com.example.domain_server.domain.usecase.FetchServerUseCase.Output
import com.example.domain_server.domain.usecase.FetchServerUseCase.Output.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchServerUseCaseImpl @Inject constructor(
    private val repository: ServerRepository,
    private val dispatcherProvider: BaseDispatcherProvider
) : FetchServerUseCase {
    override fun execute(input: FetchServerUseCase.Input): Flow<Output> {
        return repository.fetchServer().map { servers ->
            Success(servers) as Output
        }.catch { exception ->
            if (exception.isNetworkException()) {
                emit(Output.NetworkError)
            } else {
                emit(Output.UnknownError(exception.message.orEmpty()))
            }
        }.flowOn(dispatcherProvider.io())
    }
}