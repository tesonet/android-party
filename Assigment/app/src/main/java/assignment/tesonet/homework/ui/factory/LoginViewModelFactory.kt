package assignment.tesonet.homework.ui.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import assignment.tesonet.homework.api.ApiService
import assignment.tesonet.homework.ui.viewmodel.LoginViewModel

class LoginViewModelFactory(private val service: ApiService) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
