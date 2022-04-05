package com.thescriptan.tesonetparty.login

import androidx.lifecycle.ViewModel
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val navigator: Navigator) : ViewModel() {

    fun navigateToList() {
        navigator.navigateTo(Screen.LIST)
    }
}