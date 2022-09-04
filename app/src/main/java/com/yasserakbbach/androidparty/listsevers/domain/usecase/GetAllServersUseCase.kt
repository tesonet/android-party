package com.yasserakbbach.androidparty.listsevers.domain.usecase

import com.yasserakbbach.androidparty.listsevers.domain.model.Server
import com.yasserakbbach.androidparty.listsevers.domain.repository.ServerRepository
import com.yasserakbbach.androidparty.util.Resource
import kotlinx.coroutines.flow.Flow

class GetAllServersUseCase(
    private val serverRepository: ServerRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<Server>>> =
        serverRepository.getServers()
}