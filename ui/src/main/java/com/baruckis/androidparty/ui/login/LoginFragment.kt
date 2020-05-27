package com.baruckis.androidparty.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.ui.R
import com.baruckis.androidparty.ui.databinding.FragmentLoginBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    companion object {
        const val TAG = "login_fragment_tag"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.let { activity ->

            loginViewModel =
                ViewModelProvider(activity, viewModelFactory).get(LoginViewModel::class.java)

            binding = FragmentLoginBinding.inflate(inflater, container, false)

            binding.buttonLogin.setOnClickListener {
                if (binding.inputUsername.text.isNullOrBlank()) {
                    binding.inputUsername.error = getString(R.string.username_input_error)
                    binding.inputUsername.requestFocus()
                    return@setOnClickListener
                }
                if (binding.inputPassword.text.isNullOrBlank()) {
                    binding.inputPassword.error = getString(R.string.password_input_error)
                    binding.inputPassword.requestFocus()
                    return@setOnClickListener
                }
                loginViewModel.login(
                    binding.inputUsername.text.toString(),
                    binding.inputPassword.text.toString()
                )
            }

        }

        return binding.root
    }

}