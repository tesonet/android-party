package com.czech.androidparty.repositories

import android.util.Log
import com.czech.androidparty.datasource.network.ApiService
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse
import com.czech.androidparty.utils.DataState
import io.ktor.client.features.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class LoginRepository(
    private val apiService: ApiService
) {
    fun execute(
        userData: LoginRequest
    ): Flow<DataState<LoginResponse>> {
        return flow<DataState<LoginResponse>> {

            emit(DataState.loading())

            val response = apiService.login(userData)

            try {
                if (response.token == null) {
                    emit(DataState.error(message = response.message.toString()))
                    Log.d("REPOSITORY_ERROR", response.message.toString())
                }else {
                    emit(DataState.data(data = response))
                }
            }catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message.toString()
                    )
                )
            }
        }
//            .flowOn(Dispatchers.IO)
    }
}