package com.example.core.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<Input : BaseUseCase.Input, Output : BaseUseCase.Output> {
    fun execute(input: Input): Flow<Output>

    interface Input

    interface Output
}
