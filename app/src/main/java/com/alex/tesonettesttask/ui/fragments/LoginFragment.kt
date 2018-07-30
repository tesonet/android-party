package com.alex.tesonettesttask.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.TesonetApplication
import com.alex.tesonettesttask.logic.network.request.LoginRequest
import com.alex.tesonettesttask.logic.utils.hide
import com.alex.tesonettesttask.logic.utils.hideKeyboard
import com.alex.tesonettesttask.logic.utils.presentFragment
import com.alex.tesonettesttask.logic.utils.show
import com.alex.tesonettesttask.ui.interfaces.LoginView
import com.alex.tesonettesttask.ui.presenters.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        TesonetApplication.instance.mainComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        presenter.loginView = this
        loginButton?.setOnClickListener { login() }
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun login() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        hideErrorMessage()
        activity?.hideKeyboard()
        loadingView.setLoadingText(getString(R.string.logging_in))
        // start loading after the animation ends
        with(AnimatorSet()) {
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    presenter.login(LoginRequest(username, password))
                }
            })
            playTogether(ObjectAnimator.ofFloat(logo, "alpha", 1f, 0f),
                    ObjectAnimator.ofFloat(inputLayout, "alpha", 1f, 0f),
                    ObjectAnimator.ofFloat(loadingView, "alpha", 0f, 1f))
            start()
        }
    }

    override fun onError(message: String) {
        showLogin()
        showErrorMessage(message)
    }

    private var hideErrorMessageRunnable = Runnable { errorText?.hide() }

    private fun showErrorMessage(message: String) {
        with(errorText) {
            removeCallbacks(hideErrorMessageRunnable)
            text = message
            show()
            postDelayed(hideErrorMessageRunnable, 3000)
        }
    }

    private fun hideErrorMessage() {
        with(errorText) {
            removeCallbacks(hideErrorMessageRunnable)
            hide()
        }
    }

    private fun showLogin() {
        loadingView.hide()
        logo.show()
        inputLayout.show()
    }

    override fun onLogin() {
        loadingView.setLoadingText(getString(R.string.fetching_list))
        presenter.fetchServers()
    }

    override fun onServersFetched() {
        activity?.presentFragment(ServersFragment())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            usernameEditText?.setText(it.getString(USERNAME))
            passwordEditText?.setText(it.getString(PASSWORD))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        usernameEditText?.text?.toString()?.let { outState.putString(USERNAME, it) }
        passwordEditText?.text?.toString()?.let { outState.putString(PASSWORD, it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // remove runnable to avoid memory leaks
        errorText?.removeCallbacks(hideErrorMessageRunnable)
    }

    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }

}