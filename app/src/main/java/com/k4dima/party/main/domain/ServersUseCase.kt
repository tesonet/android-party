package com.k4dima.androidparty.features.main.domain

import com.k4dima.androidparty.features.app.domain.UseCase
import com.k4dima.party.main.data.model.Server

interface ServersUseCase : UseCase<Unit, List<Server>> {
    fun logout()
}