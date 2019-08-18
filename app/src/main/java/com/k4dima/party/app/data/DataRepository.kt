package com.k4dima.party.app.data

interface DataRepository<Input, Output> {
    suspend fun data(parameter: Input): Output
}