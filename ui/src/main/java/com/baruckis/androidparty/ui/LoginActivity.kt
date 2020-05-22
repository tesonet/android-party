package com.baruckis.androidparty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.viewmodels.LoginViewModel
import com.baruckis.androidparty.ui.databinding.ActivityLoginBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        // Obtain ViewModel from ViewModelProviders, using this activity as LifecycleOwner.
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            viewModel = loginViewModel
        }

        setContentView(binding.root)
    }
}
