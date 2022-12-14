package main.domain

import app.data.api.TesonetService
import app.domain.DataRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import main.domain.model.Server
import javax.inject.Inject

@ActivityRetainedScoped
class ServersRepository
@Inject
constructor(private val tesonetService: TesonetService) : DataRepository<String, List<Server>> {
    override suspend fun data(parameter: String) = tesonetService.servers("Bearer $parameter")
}