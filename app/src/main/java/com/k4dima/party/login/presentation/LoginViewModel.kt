package com.k4dima.party.login.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k4dima.party.app.domain.UseCase
import com.k4dima.party.login.ui.di.LoginScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@LoginScope
class LoginViewModel
@Inject
constructor(private val useCase: UseCase<Array<String>, String>) : ViewModel() {
    val error = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    var username = ""
    var password = ""
    fun login(@Suppress("UNUSED_PARAMETER") button: View) {
        viewModelScope.launch {
            try {
                token.postValue(useCase.data(arrayOf(username, password)))
            } catch (e: Exception) {
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
        }
    }
}