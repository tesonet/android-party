package com.example.android_party.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.android_party.R
import com.example.android_party.databinding.LoginActivityBinding
import com.example.android_party.injection.ViewModelFactory
import com.example.android_party.ui.servers.ServersActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory()).get(LoginViewModel::class.java)
        viewModel.loginSuccess.observe(this, { value -> if (value) startServersActivity() })
        viewModel.loginError.observe(this, { value -> showToast(value) })
        binding.viewModel = viewModel
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun startServersActivity() {
        startActivity(Intent(this, ServersActivity::class.java))
        finish()
    }
}