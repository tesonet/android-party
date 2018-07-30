package com.alex.tesonettesttask.logic.model

import com.alex.tesonettesttask.logic.network.TesonetService
import com.alex.tesonettesttask.logic.network.request.LoginRequest
import com.alex.tesonettesttask.logic.network.response.AccessTokenResponse
import com.alex.tesonettesttask.logic.utils.Preferences
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class LoginModel @Inject constructor(private val tesonetService: TesonetService,
                                     private val preferences: Preferences) {

    fun login(request: LoginRequest): Observable<Response<AccessTokenResponse>> {
        return tesonetService.requestToken(request)
    }

    fun saveToken(token: String) {
        preferences.setAuthToken(token)
    }

    fun removeToken() {
        preferences.removeToken()
    }

}