package com.playground.ugnius.homework.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.viewanimator.ViewAnimator
import com.playground.ugnius.homework.R
import com.playground.ugnius.homework.global.App
import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.ServersRepository
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.UserRequest
import com.playground.ugnius.homework.presenters.LoginPresenter
import com.playground.ugnius.homework.views.activites.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), LoginView {

    @Inject lateinit var apiClient: ApiClient
    @Inject lateinit var serversRepository: ServersRepository
    private val presenter by lazy { LoginPresenter(this, apiClient, serversRepository) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.applicationContext as? App)?.mainComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewAnimator.animate(testio)
            .translationY(-300F, 0F)
            .fadeIn()
            .startDelay(800)
            .duration(500)
            .andAnimate(loginContainer)
            .fadeIn()
            .duration(500)
            .onStop { loginButton?.setOnClickListener { login() } }
            .start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            usernameInput?.setText(it.getString("username"))
            passwordInput?.setText(it.getString("password"))
        }
    }

    private fun login() {
        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()
        (activity as? MainActivity)?.idlingResource?.increment()
        presenter.login(UserRequest(username, password))
        ViewAnimator.animate(loginContainer)
            .duration(250)
            .fadeOut()
            .onStart { toggleClicks(false) }
            .onStop {
                val message = context!!.getString(R.string.loading_message)
                loader?.playLoadingAnimation(message)
            }
            .start()
    }

    private fun toggleClicks(toggle: Boolean) {
        usernameInput?.isFocusableInTouchMode = toggle
        passwordInput?.isFocusableInTouchMode = toggle
        loginButton?.isClickable = toggle
    }

    override fun showError() {
        val message = context!!.getString(R.string.invalid_credentials)
        loader?.playErrorAnimation(message) {
            ViewAnimator.animate(loginContainer)
                .duration(250)
                .onStart { toggleClicks(true) }
                .onStop { (activity as? MainActivity)?.idlingResource?.decrement() }
                .fadeIn()
                .start()
        }

    }

    override fun goToServersFragment() {
        (activity as? MainActivity)?.let {
            it.navigate(ServersFragment())
            it.idlingResource.decrement()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        usernameInput?.text?.toString()?.let { outState.putString("username", it) }
        passwordInput?.text?.toString()?.let { outState.putString("password", it) }
    }

}