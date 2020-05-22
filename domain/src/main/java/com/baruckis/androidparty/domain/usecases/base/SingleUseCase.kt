package com.baruckis.androidparty.domain.usecases.base

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<Results, in Params> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
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
            .subscribeOn(backgroundScheduler)  // Thread you need the work to perform on.
            .observeOn(foregroundScheduler) // Thread you need to handle the result on.
        addDisposable(single.subscribeWith(observer)) // Handle the result here.
    }

}