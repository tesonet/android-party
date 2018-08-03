package com.k4dima.androidparty.features.login.ui

import android.os.Bundle
import android.text.style.ForegroundColorSpan
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.k4dima.androidparty.R
import com.k4dima.androidparty.databinding.ActivityLoginBinding
import com.k4dima.androidparty.features.login.presentation.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model = ViewModelProviders.of(this@LoginActivity, viewModelFactory)
                .get(LoginViewModel::class.java)
        binding.model = model
        model.token.observe(this, Observer {
            finish()
        })
        model.error.observe(this, Observer {
            binding.password.error = it
        })
        val appName = getString(R.string.app_name).toSpannable()
        val length = appName.length
        appName[length - 1..length] = ForegroundColorSpan(resources.getColor(R.color.color_secondary))
        binding.title.text = appName
    }

    /*override fun onBackPressed() {
        finishAffinity()
    }*/
}