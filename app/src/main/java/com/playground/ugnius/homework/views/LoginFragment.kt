package com.playground.ugnius.homework.views

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.viewanimator.ViewAnimator
import com.playground.ugnius.homework.R
import com.playground.ugnius.homework.global.App
import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.UserRequest
import com.playground.ugnius.homework.presenters.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), LoginView {

    @Inject lateinit var apiClient: ApiClient
    @Inject lateinit var preferences: SharedPreferences
    private val presenter by lazy { LoginPresenter(this, apiClient, preferences) }

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
            .thenAnimate(loginContainer)
            .fadeIn()
            .duration(500)
            .onStop { loginButton.setOnClickListener { login() } }
            .start()
    }

    private fun login() {
        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()
        presenter.requestToken(UserRequest(username, password))
        ViewAnimator.animate(loginContainer)
            .duration(250)
            .fadeOut()
            .onStart { toggleClicks(false) }
            .onStop {
                val message = context!!.getString(R.string.loading_message)
                loader.playLoadingAnimation(message)
            }
            .start()
    }

    private fun toggleClicks(toggle: Boolean) {
        usernameInput.isFocusableInTouchMode = toggle
        passwordInput.isFocusableInTouchMode = toggle
        loginButton.isClickable = toggle
    }

    override fun showError() {
        val message = context!!.getString(R.string.invalid_credentials)
        loader.playErrorAnimation(message) {
            ViewAnimator.animate(loginContainer)
                .duration(250)
                .onStart { toggleClicks(true) }
                .fadeIn()
                .start()
        }

    }

    override fun goToServersFragment() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.clear()
    }

}
