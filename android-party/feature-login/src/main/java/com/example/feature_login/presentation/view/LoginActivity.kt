package com.example.feature_login.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.R.anim
import com.example.core.R.string
import com.example.core.ext.viewBinding
import com.example.feature_login.databinding.ActivityLoginBinding
import com.example.feature_login.presentation.viewmodel.LoginContract
import com.example.feature_login.presentation.viewmodel.LoginViewModel
import com.example.feature_server.presentation.view.activity.ServerActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val binding: ActivityLoginBinding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewState()
        observeEffect()

        binding.login.setOnClickListener {
            loginViewModel.onUiEvent(
                LoginContract.Event.OnLoginClicked(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            )
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.viewState.collect { viewState ->
                    setLoading(viewState.isLoading)
                    setUserNameAndPassword(viewState.userName, viewState.password)
                }
            }
        }
    }

    private fun setUserNameAndPassword(userName: String, password: String) {
        binding.username.setText(userName)
        binding.password.setText(password)
    }

    private fun observeEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.effect.collect { effect ->
                    when (effect) {
                        is LoginContract.Effect.NetworkErrorEffect -> {
                            showSnackBar(getString(string.network_error))
                        }
                        is LoginContract.Effect.UnknownErrorEffect -> {
                            showSnackBar(getString(string.unknown_error))
                        }
                        is LoginContract.Effect.UserNameErrorEffect -> {
                            binding.username.error = getString(effect.message)
                        }
                        is LoginContract.Effect.PasswordErrorEffect -> {
                            binding.password.error = getString(effect.message)
                        }

                        is LoginContract.Effect.OnNavigationEffect -> {
                            navigationToLoading()
                        }
                    }
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.container, text, Snackbar.LENGTH_LONG).show()
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

    private fun navigationToLoading() {
        startActivity(
            Intent(this, ServerActivity::class.java)
        )
        overridePendingTransition(anim.slide_in_right, anim.slide_out_left)
    }
}