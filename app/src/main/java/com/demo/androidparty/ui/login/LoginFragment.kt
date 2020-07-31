package com.demo.androidparty.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.demo.androidparty.BaseFragment
import com.demo.androidparty.R
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    override val layoutId: Int = R.layout.login_fragment

    @Inject
    internal lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewListeners()
        observeLiveData()
    }

    private fun setViewListeners() {
        loginButton.setOnClickListener {
            viewModel.login("tesonet", "partyanimal")
        }
    }

    private fun observeLiveData() {
        viewModel.loggedIn.observeNullable {
            findNavController().navigate(R.id.goToServerListFragment)
        }
        viewModel.error.observe(::showToast)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}