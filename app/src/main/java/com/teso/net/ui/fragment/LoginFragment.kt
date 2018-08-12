package com.teso.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teso.net.R
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.LoginFragmentVM
import com.teso.net.utils.onClick
import com.teso.net.utils.showNewFragment
import com.teso.net.utils.showSnack
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginFragmentVM

    override fun getName(): String = "Login fragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentVM::class.java)
        viewModel.getError().observe(this, Observer { activity?.showSnack(it) })
        viewModel.getNextScreen().observe(this, Observer { openNextScreen() })
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun openNextScreen() {
        Timber.d("Open loading screen")
        activity?.showNewFragment(LoadingFragment::class.java, false, loginBg, getString(R.string.pictureTransitionName))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.onClick { login() }
    }

    private fun login() {
        Timber.d("Login was clicked")
        if (isClickAllowed()) viewModel.login(loginName.getText(), loginPassword.getText())
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkIfUserAlreadyLogin()
    }
}