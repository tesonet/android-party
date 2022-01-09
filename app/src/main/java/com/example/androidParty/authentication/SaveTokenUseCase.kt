//package com.example.androidParty.presentation.login.domain.usecase
//
//import com.example.androidParty.presentation.login.LoginRepository
//import com.example.androidParty.presentation.serverList.ServerRepository
//import javax.inject.Inject
//
//class SaveTokenUseCase @Inject constructor(
//    private val loginRepository: LoginRepository
//) {
//    suspend operator fun invoke(accessToken: String) {
//        return loginRepository.saveAccessToken(accessToken)
//    }
//}