package com.example.android_party.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.android_party.ui.login.LoginViewModel
import com.example.android_party.ui.servers.ServersViewModel

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ServersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServersViewModel() as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}