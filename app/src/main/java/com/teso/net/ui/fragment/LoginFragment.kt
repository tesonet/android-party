package com.teso.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.ui.adapters.SiteAdapter
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.LoginFragmentVM
import com.teso.net.ui.vm.SiteFragmentVM
import com.teso.net.utils.onClick
import com.teso.net.utils.showSnack
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : BaseFragment() {

    override fun getName(): String = "Login fragment"

    private lateinit var viewModel: LoginFragmentVM
    private val listSites: ArrayList<ServerEntity> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(LoginFragmentVM::class.java)
        viewModel.getError().observe(this, Observer { activity?.showSnack(it) })
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.onClick { login() }
    }

    private fun login() {
        Timber.d("Login was clicked")
        viewModel.login(loginName.getText(), loginPassword.getText())
    }
}