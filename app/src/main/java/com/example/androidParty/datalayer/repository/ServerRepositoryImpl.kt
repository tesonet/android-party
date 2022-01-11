package com.example.androidParty.datalayer.repository

import com.example.androidParty.datalayer.dto.toServer
import com.example.androidParty.datalayer.network.BaseDataSource
import com.example.androidParty.datalayer.network.NordApiService
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.serverList.ServerRepository
import com.example.androidParty.presentation.serverList.domain.entity.Server
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ServerRepositoryImpl(
  private val nordApiService: NordApiService
) : ServerRepository, BaseDataSource() {

  override fun getServerData(token: String): Flow<Resource<List<Server>>> = flow {
    emit(Resource.Loading())
    val result = getResult { nordApiService.getDataServers(token) }
    if (result.data != null) {
      val serverList = result.data.map { it.toServer() }
      emit(Resource.Success(serverList))
    } else {
      emit(Resource.Error<List<Server>>("Error"))
    }
  }
}