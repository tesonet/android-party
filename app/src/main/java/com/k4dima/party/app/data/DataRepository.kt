package com.k4dima.party.app.data

import io.reactivex.Single

interface DataRepository<Input, Output> {
    fun data(parameter: Input): Single<Output>
}