package com.amaximan.tesonet.viewModel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.amaximan.tesonet.model.networking.TokenRequest
import com.amaximan.tesonet.other.StateManager
import com.amaximan.tesonet.other.networking.Repository

class AuthVM : ViewModel() {
    val isLoadingToken: ObservableField<Boolean> = StateManager.instance.getTokenIsInProgress
    val isLoadingServers: ObservableField<Boolean> = StateManager.instance.getServersIsInProgress

    var username: String? = null
    var password: String? = null

    val usernameError = ObservableField<String>()
    val passwordError = ObservableField<String>()

    fun getToken() {
        if (username.isNullOrEmpty()) {
            usernameError.set("Could not be empty")
            usernameError.notifyChange()
            return
        }
        if (password.isNullOrEmpty()) {
            passwordError.set("Could not be empty")
            passwordError.notifyChange()
            return
        }

        if (username != null && password != null) {
            Repository.instance.getToken(TokenRequest(username!!, password!!))
        }
    }
}