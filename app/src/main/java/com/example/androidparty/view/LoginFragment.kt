package com.example.androidparty.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.androidparty.R
import com.example.androidparty.ResponseListener
import com.example.androidparty.databinding.FragmentLoginBinding
import com.example.androidparty.networking.LoginResult
import com.example.androidparty.viewmodel.LoginViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class LoginFragment(
    val listener: FragmentsNavigationListener
) : DaggerFragment() {

    lateinit var binder: FragmentLoginBinding
    @Inject
    lateinit var loginViewModel: LoginViewModel
    private var loginIsFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binder.loginViewModel = loginViewModel
        binder.loginButton.setOnClickListener { startLogin() }
        return binder.root
    }

    private fun sendCredentials() {
        loginViewModel.sendCredentials(object : ResponseListener<LoginResult> {
            override fun <T> onResult(data: T) {

                when (data as LoginResult) {
                    LoginResult.Success -> {
                        listener.onLoginClicked()
                    }
                    LoginResult.Failed -> {
                        setUIBack()
                        Toast.makeText(activity, "Wrong credentials", Toast.LENGTH_LONG).show()
                    }
                    LoginResult.EmptyFields -> {
                        setUIBack()
                        binder.userName.error = "Field cannot be empty"
                        binder.password.error = "Field cannot be empty"
                    }
                    LoginResult.FallbackError -> {
                        Toast.makeText(activity, "Unknown error occurred", Toast.LENGTH_LONG).show()
                    }
                }
                loginIsFinished = true
            }
        })
    }

    private fun animateLogin() {
        val animFadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out)

        binder.passwordInputLayout.startAnimation(animFadeOut)
        binder.usernameInputLayout.startAnimation(animFadeOut)
        binder.loginButton.startAnimation(animFadeOut)
        binder.logo.startAnimation(animFadeOut)

        binder.loginProgressBar.visibility = View.VISIBLE
        binder.loginText.visibility = View.VISIBLE

    }

    private fun setUIBack(){
        val animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)

        binder.loginProgressBar.visibility = View.GONE
        binder.loginText.visibility = View.GONE

        binder.passwordInputLayout.startAnimation(animFadeIn)
        binder.usernameInputLayout.startAnimation(animFadeIn)
        binder.loginButton.startAnimation(animFadeIn)
        binder.logo.startAnimation(animFadeIn)
    }

    private fun startLogin() {
        animateLogin()
        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    sendCredentials()
                }
                if (loginIsFinished){
                    timer.cancel()
                    timer.purge()
                }
            }
        }
        timer.scheduleAtFixedRate(timerTask, 2000, 500)
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: FragmentsNavigationListener) =
            LoginFragment(listener).apply {
            }
    }
}
