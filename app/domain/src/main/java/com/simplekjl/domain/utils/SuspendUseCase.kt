package com.simplekjl.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class SuspendUseCase<in P, out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /**
     * Executes the use case asynchronously and returns a result [R].
     *
     * @return a result [R].
     *
     * @param parameters the input parameters [P] to run the use case with
     */
    suspend operator fun invoke(parameters: P): R {
        // Moving all use case's executions to the injected dispatcher
        // In tests, this becomes a TestCoroutineDispatcher
        return withContext(coroutineDispatcher) {
            execute(parameters)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract suspend fun execute(parameters: P): R
}

suspend operator fun <R> SuspendUseCase<Unit, R>.invoke(): R = this(Unit)
