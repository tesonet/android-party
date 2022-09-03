package com.yasserakbbach.androidparty.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private val TAG = "LOGIN"

    fun login() {
        viewModelScope.launch {
            val session = loginUseCase(
                Login(
                    username = "test",
                    password = "test",
                )
            ).getOrNull()
            Log.d(TAG, "login: $session")
        }
    }
}