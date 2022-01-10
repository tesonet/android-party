package com.example.androidParty.datalayer.repository

import com.example.androidParty.datalayer.network.LoginCredentials
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.LoginRepository
import com.example.androidParty.presentation.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyFakeRepository : LoginRepository {
    override fun login(body: LoginCredentials): Flow<Resource<User>> = flow {
        emit(Resource.Success(User("token")))
    }

    override suspend fun logout() {
        //do nothing
    }

    override suspend fun getAccessToken(): Flow<String?> = flow {
        emit(("token"))
    }
}