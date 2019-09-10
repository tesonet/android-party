package com.jonastiskus.testio.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.jonastiskus.testio.R
import com.jonastiskus.testio.TestioApplication
import com.jonastiskus.testio.auth.AuthCallback
import com.jonastiskus.testio.databinding.ActivityLoginBinding
import com.jonastiskus.testio.network.service.ServiceProvider

class LoginActivity : AppCompatActivity(), AuthCallback {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var serviceProvider: ServiceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val testioApplication = application as TestioApplication
        serviceProvider = testioApplication.getServiceProvider()

        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (isFormValid()) {
                serviceProvider.getAuthService().authorizeUser(username, password, this)
            } else {
                makeSnackBar(getString(R.string.TEXT_VALIDATION))
            }
        }
    }

    private fun isFormValid(): Boolean {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()

        return !(username.isEmpty() || password.isEmpty())
    }

    private fun openLoadingActivity() {
        val intent = Intent(this, LoadingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
    }

    override fun onLoginSuccessfull() {
        openLoadingActivity()
    }

    override fun onLoginFailed() {
        makeSnackBar(getString(R.string.TEXT_SOMETHING_WENT_WRONG))
    }

    private fun makeSnackBar(text : String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()

    }
}
