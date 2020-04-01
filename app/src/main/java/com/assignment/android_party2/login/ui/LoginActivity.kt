package com.assignment.android_party2.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.assignment.android_party2.R
import com.assignment.android_party2.preferences.PreferencesProvider
import com.assignment.android_party2.databinding.ActivityLoginBinding
import com.assignment.android_party2.servers.ui.ServersActivity
import com.assignment.android_party2.login.models.TokenModel
import com.assignment.android_party2.utils.toast

class LoginActivity : AppCompatActivity(), LoginCallback {

    private lateinit var sessionManager: PreferencesProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        viewModel.loginCallback = this
        sessionManager = PreferencesProvider(this)
    }

    override fun onSuccess(loginResponse: LiveData<TokenModel>) {
        loginResponse.observe(this, Observer {
            loginResponse.value?.token?.let { token -> sessionManager.saveLoginToken(token) }
            val intent = Intent(this, ServersActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onFailure(errorMessage: String) {
        toast(errorMessage)
    }
}
