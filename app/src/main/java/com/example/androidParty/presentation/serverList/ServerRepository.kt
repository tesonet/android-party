package com.example.androidParty.presentation.serverList

import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.serverList.domain.entity.Server
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun getServerData(token: String): Flow<Resource<List<Server>>>
}