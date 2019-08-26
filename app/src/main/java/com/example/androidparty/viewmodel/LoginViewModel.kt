package com.example.androidparty.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.androidparty.ResponseListener
import com.example.androidparty.networking.LoginResult
import com.example.androidparty.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(val repository: Repository) : ViewModel() {

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())
    var username: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()


    fun sendCredentials(listener: ResponseListener<LoginResult>) {
        val usernameString = username.get()
        val passwordString = password.get()
        if (usernameString != null && passwordString != null) {
            ioScope.launch {
                repository.getToken(
                    usernameString,
                    passwordString,
                    object : ResponseListener<LoginResult> {
                        override fun <T> onResult(data: T) {
                            Log.e("tag", data.toString())
                            listener.onResult(data)
                        }
                    })
            }
        } else listener.onResult(LoginResult.EmptyFields)
    }
}