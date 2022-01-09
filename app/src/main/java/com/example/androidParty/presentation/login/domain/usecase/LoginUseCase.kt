package com.example.androidParty.presentation.login.domain.usecase

import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.LoginRepository
import com.example.androidParty.presentation.login.domain.entity.User
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(body: RequestBody): Flow<Resource<User>> {
        return loginRepository.login(body)
    }
}
