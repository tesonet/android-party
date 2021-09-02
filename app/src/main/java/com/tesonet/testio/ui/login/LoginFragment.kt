package com.tesonet.testio.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.tesonet.testio.R
import com.tesonet.testio.R.string
import com.tesonet.testio.databinding.LoginFragmentBinding
import com.tesonet.testio.services.repositories.ServersRepository.Companion.ERROR_HTTP_401
import com.tesonet.testio.services.repositories.ServersRepository.Companion.UNKNOWN_ERROR
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyFields
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyName
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.EmptyPassword
import com.tesonet.testio.ui.login.LoginViewModel.UiEventLogin.FulfilledLogin
import com.tesonet.testio.utils.LoginHelper
import com.tesonet.testio.utils.Resource.Complete
import com.tesonet.testio.utils.Resource.Empty
import com.tesonet.testio.utils.Resource.Error
import com.tesonet.testio.utils.Resource.Loading
import com.tesonet.testio.utils.observeIt
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding
    private lateinit var loginHelper: LoginHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginHelper = LoginHelper(requireContext())
        checkIfUserIsLoggedIn()
        observeUiEvent()
        observeRequestToken()

        binding.buttonLogIn.setOnClickListener {
            viewModel.logIn(
                binding.editTextUserName.text.toString().trim(),
                binding.editTextPassword.text.toString().trim()
            )
        }

        // easter egg
        binding.imageViewCompanyLogo.setOnLongClickListener {
            presetLogin()
            true
        }
    }

    private fun observeUiEvent() {
        viewModel.uiLoginEvent.observeIt(this) { event ->
            when (event) {
                EmptyFields -> {
                    showToast(getString(string.fill_all_fields))
                }
                EmptyName -> {
                    showToast(getString(string.enter_name))
                }
                EmptyPassword -> {
                    showToast(getString(string.enter_password))
                }
                is FulfilledLogin -> {
                    viewModel.getAccessToken(event.requestUser)
                }
                null -> {
                    // no action needed
                }
            }
        }
    }

    private fun observeRequestToken() {
        viewModel.requestToken.observeIt(this) { requestTokenState ->
            binding.progressBarLogin.visibility = View.GONE

            when (requestTokenState) {
                is Complete -> {
                    saveUserLoginState()
                    val bundle = bundleOf(REQUEST_TOKEN to requestTokenState.value)
                    findNavController().navigate(R.id.navigation_loginFragment_to_loadingFragment, bundle)
                }
                is Empty -> {
                    showToast(getString(string.empty_token))
                    changeButtonState(true)
                }
                is Error -> {
                    showToast(getErrorMessage(requestTokenState.error))
                    changeButtonState(true)
                }
                is Loading -> {
                    binding.progressBarLogin.visibility = View.VISIBLE
                    changeButtonState(false)
                }
                else -> {
                    changeButtonState(true)
                }
            }
        }
    }

    private fun getErrorMessage(message: String?): String? {
        return if (message == ERROR_HTTP_401) {
            getString(string.incorrect_login)
        } else {
            message
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message ?: UNKNOWN_ERROR, Toast.LENGTH_SHORT).show()
    }

    private fun changeButtonState(enabled: Boolean) {
        if (enabled) {
            binding.buttonLogIn.apply {
                isEnabled = true
                alpha = 1f
            }
        } else {
            binding.buttonLogIn.apply {
                isEnabled = false
                alpha = .8f
            }
        }
    }

    private fun saveUserLoginState() {
        loginHelper.logIn()
    }

    private fun checkIfUserIsLoggedIn() {
        if (loginHelper.isLoggedIn()) {
            val navOptions = NavOptions.Builder().apply {
                setPopUpTo(R.id.loginFragment, true)
            }.build()

            findNavController().navigate(R.id.serversFragment, null, navOptions)
        }
    }

    private fun presetLogin() {
        binding.editTextUserName.setText(EXTRA_USER_NAME)
        binding.editTextPassword.setText(EXTRA_USER_PASSWORD)
    }

    companion object {
        const val REQUEST_TOKEN: String = "request_token"
        const val EXTRA_USER_NAME: String = "tesonet"
        const val EXTRA_USER_PASSWORD: String = "partyanimal"
    }
}