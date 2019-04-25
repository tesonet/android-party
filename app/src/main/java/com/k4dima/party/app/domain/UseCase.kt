package com.k4dima.party.app.domain

import io.reactivex.Single

interface UseCase<Input, Output> {
    fun data(params: Input): Single<Output>
}