package com.example.androidParty.presentation.serverList.domain.usecase

import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.serverList.ServerRepository
import com.example.androidParty.presentation.serverList.domain.entity.Server
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServerListUseCase @Inject constructor(
    private val serverRepository: ServerRepository
) {
    operator fun invoke(token: String): Flow<Resource<List<Server>>> {
        return serverRepository.getServerData(token)
    }
}