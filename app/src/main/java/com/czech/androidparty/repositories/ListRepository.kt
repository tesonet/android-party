package com.czech.androidparty.repositories

import com.czech.androidparty.datasource.cache.AndroidPartyCache
import com.czech.androidparty.datasource.network.ApiService
import com.czech.androidparty.models.DataList
import com.czech.androidparty.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val apiService: ApiService,
    private val androidPartyCache: AndroidPartyCache
) {
    fun getFromNetwork(
        token: String
    ): Flow<DataState<List<DataList>>> {
        return flow {

            val response = apiService.getList(token)

            emit(DataState.loading())

            if (response != null) {
                androidPartyCache.insertData(response)
            }

            val cacheResponse = androidPartyCache.getData()

            try {
                emit(DataState.data(data = cacheResponse))
            }catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getFromDatabase(): Flow<DataState<List<DataList>>> {
        return flow {

            val cacheData = androidPartyCache.getData()

            try {
                emit(DataState.data(data = cacheData))
            }catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}