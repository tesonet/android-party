package com.baruckis.androidparty.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.usecases.Login
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: Login
) : ViewModel() {

    fun login(username: String, password: String) {
        login.execute(
            LoginSubscriber(),
            Login.Params.authorization(username, password)
        )
    }

    fun loginClick() {
        login("tesonet", "partyanimala")
    }

    override fun onCleared() {
        login.dispose()
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