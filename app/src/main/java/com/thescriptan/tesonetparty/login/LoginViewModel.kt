package com.thescriptan.tesonetparty.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thescriptan.tesonetparty.login.model.LoginRequest
import com.thescriptan.tesonetparty.login.repository.LoginRepository
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val navigator: Navigator, private val repository: LoginRepository) : ViewModel() {

    fun navigateToList() {
        navigator.navigateTo(Screen.LIST)
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val test = repository.login(loginRequest)
        }
    }
}