package com.baruckis.androidparty.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
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

            binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
                viewModel = loginViewModel
            }

            loginViewModel.loginResource.observe(activity, Observer {resource ->
                handleLoginPresentationResourceStatus(resource)
            })

        }

        return binding.root
    }

    private fun handleLoginPresentationResourceStatus(dataResource: Resource<LoginPresentation>) {

        when (dataResource.status) {
            Status.LOADING -> {
            }
            Status.SUCCESS -> {

            }
            Status.ERROR -> {
            }
        }
    }

}