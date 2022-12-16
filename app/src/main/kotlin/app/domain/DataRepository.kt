package app.domain

interface DataRepository<Input, Output> {
    suspend fun data(parameter: Input): Output
}