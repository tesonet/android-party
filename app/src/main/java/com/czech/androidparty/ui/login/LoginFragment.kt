package com.czech.androidparty.ui.login

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.czech.androidparty.R
import com.czech.androidparty.connection.NetworkConnection
import com.czech.androidparty.databinding.LoginFragmentBinding
import com.czech.androidparty.responseStates.LoginState
import com.czech.androidparty.utils.*
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<LoginViewModel>()

    private lateinit var networkConnection: NetworkConnection


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        val transitionInflater = TransitionInflater.from(requireContext())
        exitTransition = transitionInflater.inflateTransition(R.transition.slide_left)

        enterTransition = transitionInflater.inflateTransition(R.transition.slide_right)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNetwork()

        observeLogin()

        binding.loginButton.setOnClickListener {
            hideKeyboard(requireActivity(), it)
            observeNetwork()
                if (isFieldsValid()) {
                viewModel.login(
                    username = binding.usernameEdittext.text.toString(),
                    password = binding.passwordEdittext.text.toString()
                )
            }
        }
    }

    private fun observeNetwork() {
        networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (!isConnected) {
                requireActivity().showErrorDialog("You do not have an active internet connection. Please establish a connection and try again.")
            }
        }
    }

    private fun isFieldsValid(): Boolean {
        return when {
            binding.usernameEdittext.text.isEmpty() -> {
                showShortSnackBar("Username field cannot be empty")
                false
            }
            binding.passwordEdittext.text.isEmpty() -> {
                showShortSnackBar("Password field cannot be empty")
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
                        showProgress(true)
                        run { delay(2000) }
                        binding.enterFields.hide()
                        binding.loader.show()
                        run { delay(2000) }
                    }
                    is LoginState.Error -> {
                        binding.enterFields.show()
                        binding.loader.hide()
                        showProgress(false)
                        it.message.let { errorMessage ->
                            requireActivity().showErrorDialog("$errorMessage. Invalid username or password")
                        }
                    }
                    is LoginState.Success -> {
                        launchFragment(LoginFragmentDirections.actionLoginFragmentToListFragment())
                        viewModel.loginState.value = null
                    }
                    else -> {}
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}