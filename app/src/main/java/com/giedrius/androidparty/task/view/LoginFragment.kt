package com.giedrius.androidparty.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.giedrius.androidparty.R
import com.giedrius.androidparty.task.navigation.NavigationListener
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.server.login.LoginOutcome
import com.giedrius.androidparty.task.viewmodel.LoginViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

class LoginFragment(val navListener: NavigationListener) : DaggerFragment() {
    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_login, container, false)
        view.login_button.setOnClickListener {
            login_progress_bar.visibility = View.VISIBLE
            login_text.visibility = View.VISIBLE
            sendCredentials(view.user_name_edit_text.text.toString(), view.password_edit_text.text.toString())
        }
        return view
    }

    private fun sendCredentials(username: String, password: String) {
        loginViewModel.login(object : ApiListener<LoginOutcome> {
            override fun <T> onResult(data: T) {
                when (data as LoginOutcome) {
                    LoginOutcome.SUCCESSFUL -> { navListener.onLoginPerformed() }
                    LoginOutcome.UNSUCCESSFUL -> { Toast.makeText(activity, "Login unsuccessful", Toast.LENGTH_LONG).show() }
                }
                login_text.visibility = View.GONE
                login_progress_bar.visibility = View.GONE
            }
        }, username, password)
    }

    companion object {
        @JvmStatic fun newInstance(listener: NavigationListener) = LoginFragment(listener).apply {}
    }
}
