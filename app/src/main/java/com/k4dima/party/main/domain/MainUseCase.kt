package com.k4dima.party.main.domain

import com.k4dima.party.app.data.DataRepository
import com.k4dima.party.app.data.PreferenceRepository
import com.k4dima.party.main.data.model.Server
import com.k4dima.party.main.ui.di.MainScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@MainScope
class MainUseCase
@Inject
constructor(private val serversRepository: DataRepository<String, List<Server>>,
            private val preferenceRepository: PreferenceRepository) : ServersUseCase {
    override suspend fun data(params: Unit) =
            withContext(Dispatchers.IO) {
                val token = preferenceRepository.token ?: throw AuthenticationException()
                serversRepository.data(token)
            }

    override fun logout() {
        preferenceRepository.token = null
    }
}