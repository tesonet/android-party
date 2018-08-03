package com.k4dima.androidparty.features.app.data

import io.reactivex.Single

interface DataRepository<Input, Output> {
    fun data(parameter: Input): Single<Output>
}