package main.domain

import app.domain.DataRepository
import app.data.api.TesonetService
import main.domain.model.Server
import main.ui.di.MainScope
import javax.inject.Inject

@MainScope
class ServersRepository
@Inject
constructor(private val tesonetService: TesonetService) : DataRepository<String, List<Server>> {
    override suspend fun data(parameter: String) = tesonetService.servers("Bearer $parameter")
}