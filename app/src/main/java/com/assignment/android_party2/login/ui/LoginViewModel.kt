package com.assignment.android_party2.login.ui

import android.view.View
import androidx.lifecycle.ViewModel
import com.assignment.android_party2.login.repo.LoginRepository
import com.assignment.android_party2.utils.Coroutines

class LoginViewModel : ViewModel() {

    var username: String? = null
    var password: String? = null
    var loginCallback: LoginCallback? = null

    fun onLoginButtonClicked(view: View) {

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginCallback?.onFailure("Invalid username or password!")
            return
        }

        Coroutines.main {
            LoginRepository().userLogin(username!!, password!!, loginCallback)
        }
    }
}