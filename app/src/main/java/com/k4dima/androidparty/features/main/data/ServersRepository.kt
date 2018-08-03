package com.k4dima.androidparty.features.main.data

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.app.data.api.TesonetService
import com.k4dima.androidparty.features.main.data.model.Server
import com.k4dima.androidparty.features.main.ui.di.MainScope
import javax.inject.Inject

@MainScope
class ServersRepository
@Inject
constructor(private val tesonetService: TesonetService) : DataRepository<String, List<Server>> {
    override fun data(parameter: String) = tesonetService.servers("Bearer $parameter")
}