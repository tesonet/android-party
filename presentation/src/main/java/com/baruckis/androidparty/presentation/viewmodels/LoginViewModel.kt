package com.baruckis.androidparty.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.usecases.SendAuthorization
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sendAuthorization: SendAuthorization
) : ViewModel() {

    fun sendAuthorization(username: String, password: String) {
        sendAuthorization.execute(
            TokenSubscriber(),
            SendAuthorization.Params.authorization(username, password)
        )
    }

    fun sendAuthorizationClick() {
        sendAuthorization("tesonet", "partyanimal")
    }

    override fun onCleared() {
        sendAuthorization.dispose()
        super.onCleared()
    }

    inner class TokenSubscriber : DisposableSingleObserver<TokenEntity>() {

        override fun onSuccess(t: TokenEntity) {
            Log.d("AndroidParty", "onSuccess - Token " + t.token)
        }

        override fun onError(e: Throwable) {
            Log.d("AndroidParty", "onError")
        }

    }

}