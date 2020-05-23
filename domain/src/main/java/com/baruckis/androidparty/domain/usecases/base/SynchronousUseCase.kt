package com.baruckis.androidparty.domain.usecases.base

interface SynchronousUseCase<out Results, in Params> {

    /**
     * Executes the current use case and returns the result immediately.
     * If this should not return anything then use [Unit] as [Results].
     */
    fun execute(params: Params? = null): Results

}