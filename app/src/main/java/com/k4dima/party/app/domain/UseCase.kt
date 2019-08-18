package com.k4dima.party.app.domain

interface UseCase<Input, Output> {
    suspend fun data(params: Input): Output
}