package com.simplekjl.domain.utils

/**
 *  Executes business logic, should be implemented for all use cases that doesn't necessary need to run on coroutines
 */
abstract class UseCase<in P, out R> {

    /**
     * Executes the use case returns a result [R].
     *
     * @return a result [R].
     *
     * @param parameters the input parameters [P] to run the use case with
     */
    operator fun invoke(parameters: P): R {
        return execute(parameters)
    }

    /**
     * Override this to set the code to be executed.
     */
    protected abstract fun execute(parameters: P): R
}

operator fun <R> UseCase<Unit, R>.invoke() = this(Unit)
