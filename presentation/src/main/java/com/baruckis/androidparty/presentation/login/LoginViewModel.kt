package com.baruckis.androidparty.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.usecases.LoginUseCase
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(username: String, password: String) {
        loginUseCase.execute(
            LoginSubscriber(),
            LoginUseCase.Params.authorization(username, password)
        )
    }

    fun loginClick() {
        login("tesonet", "partyanimal")
    }

    override fun onCleared() {
        loginUseCase.dispose()
        super.onCleared()
    }

    inner class LoginSubscriber : DisposableSingleObserver<TokenEntity>() {

        override fun onSuccess(t: TokenEntity) {
            Log.d("AndroidParty", "onSuccess - Token " + t.token)
        }

        override fun onError(e: Throwable) {
            Log.d("AndroidParty", "onError")
        }

    }

}