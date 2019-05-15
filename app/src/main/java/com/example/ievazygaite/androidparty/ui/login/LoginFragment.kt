package com.example.ievazygaite.androidparty.ui.login

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ievazygaite.androidparty.R
import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.di.component.DaggerFragmentComponent
import com.example.ievazygaite.androidparty.di.module.FragmentModule
import com.example.ievazygaite.androidparty.ui.main.MainActivity
import com.example.ievazygaite.androidparty.utils.Tools
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), LoginContract.View {


    companion object {
        val TAG: String = "SettingsFragment"
    }

    @Inject
    lateinit var presenter: LoginContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): LoginFragment {
        return LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showErrorMessage(error: String) {
        Tools.showSnackBar(error, Color.RED, view!!)
    }

    private fun injectDependency() {
        val loginComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        loginComponent.inject(this)
    }

    private fun initView() {
        btn_login.setOnClickListener { onLoginButtonClicked() }
    }

    private fun onLoginButtonClicked() {
        if (!input_password.text.isNullOrEmpty() && !input_username.text.isNullOrEmpty()) {
            view!!.hideKeyboard()
            presenter.authorize(input_username.text.toString(), input_password.text.toString())
        }
    }

    override fun showListFragment(servers: List<Server>) {
        (activity as MainActivity).showListFragment(servers)
    }

    override fun showProgress(isShown: Boolean) {
        if (isShown) {
            container_loading.visibility = View.VISIBLE
            container_login.visibility = View.GONE
            logo.visibility = View.GONE
            progress_bar.animate()
        } else {
            container_loading.visibility = View.GONE
            container_login.visibility = View.VISIBLE
            logo.visibility = View.VISIBLE
            progress_bar.clearAnimation()
        }
    }
}


