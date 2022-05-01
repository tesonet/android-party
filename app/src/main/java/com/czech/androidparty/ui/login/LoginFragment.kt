package com.czech.androidparty.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.czech.androidparty.databinding.LoginFragmentBinding
import com.czech.androidparty.responseStates.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLogin()

    }

    private fun isFieldsValid(): Boolean {
        return when {
            binding.usernameEdittext.text.isEmpty() -> {
                false
            }
            binding.passwprdEdittext.text.isEmpty() -> {
                false
            }
            else -> true
        }
    }

    private fun observeLogin() {
        lifecycleScope.launch {
            viewModel.loginState.collect {
                when (it) {
                    is LoginState.Loading -> {
                        Log.d("LOGIN", "Loading")

                    }
                    is LoginState.Error -> {
                        it.message.let { errorMessage ->
                            Log.d("LOGIN", errorMessage)
                        }
                    }
                    is LoginState.Success -> {
                        Log.d("LOGIN", "Successful. Token: ${it.data?.token}")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}