package com.example.androidParty.datalayer.repository

import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object RemoteDataGenerator {

    fun getRemoteDataResponse(): Flow<Resource<User>> = flow {
        emit(Resource.Success(User("token")))
    }

    fun getRemoteDataErrorResponse(): Flow<Resource<User>> = flow {
        emit(Resource.Error("Error Happened"))
    }
}