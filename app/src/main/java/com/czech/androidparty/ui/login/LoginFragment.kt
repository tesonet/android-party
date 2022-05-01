package com.czech.androidparty.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.czech.androidparty.R
import com.czech.androidparty.databinding.LoginFragmentBinding
import com.czech.androidparty.responseStates.LoginState
import com.czech.androidparty.utils.disableView
import com.czech.androidparty.utils.enableView
import com.czech.androidparty.utils.hide
import com.czech.androidparty.utils.show
import com.github.razir.progressbutton.ProgressButtonUtils.Companion.hideProgress
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        viewModel.login(
            username = "tesonet",
            password = "partyanimal"
        )
    }

    private fun isFieldsValid(): Boolean {
        return when {
            binding.usernameEdittext.text.isEmpty() -> {
                false
            }
            binding.passwordEdittext.text.isEmpty() -> {
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
                        showProgress(true)
                        run { delay(3000) }
                        binding.enterFields.hide()
                        binding.loader.loadingView.show()
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

    private fun showProgress(show: Boolean) {
        binding.loginButton.apply {
            if (show) {
                disableView()
                showProgress {
                    buttonText = "Logging in..."
                    progressColor = ContextCompat.getColor(requireContext(), R.color.green)
                }
            } else {
                enableView()
                hideProgress(newText = "Login")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}