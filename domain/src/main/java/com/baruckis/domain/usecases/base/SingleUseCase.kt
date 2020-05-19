package com.baruckis.domain.usecases.base

import com.baruckis.domain.executor.ExecutionThreadScheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<Results, in Params> constructor(
    private val backgroundExecutor: ExecutionThreadScheduler,
    private val foregroundExecutor: ExecutionThreadScheduler
) : BaseReactiveUseCase() {

    /**
     * Builds an [Single] which will be used when executing the current [SingleUseCase].
     */
    abstract fun buildUseCaseSingle(params: Params? = null): Single<Results>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     * by [buildUseCaseSingle] method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableSingleObserver<Results>, params: Params? = null) {
        val single = buildUseCaseSingle(params)
            .subscribeOn(backgroundExecutor.scheduler)  // Thread you need the work to perform on.
            .observeOn(foregroundExecutor.scheduler) // Thread you need to handle the result on.
        addDisposable(single.subscribeWith(observer)) // Handle the result here.
    }

}