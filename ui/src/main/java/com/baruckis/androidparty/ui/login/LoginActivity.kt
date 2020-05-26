package com.baruckis.androidparty.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.ui.R
import com.baruckis.androidparty.ui.callback.BackCallback
import com.baruckis.androidparty.ui.main.MainActivity
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity(), BackCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        setContentView(R.layout.activity_login)

        loginViewModel.loginResource.observe(this, Observer { resource ->
            handleLoginPresentationResourceStatus(resource)
        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, LoginFragment(), LoginFragment.TAG)
                .commit()
        }

    }

    private fun handleLoginPresentationResourceStatus(dataResource: Resource<LoginPresentation>) {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

        when (dataResource.status) {
            Status.LOADING -> {

                when (currentFragment?.tag) {
                    LoginFragment.TAG -> {
                        supportFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.anim.enter_from_right, R.anim.exit_to_left,
                                R.anim.enter_from_left, R.anim.exit_to_right
                            )
                            .replace(
                                R.id.fragment_container_view,
                                LoadingFragment(this@LoginActivity),
                                LoadingFragment.TAG
                            )
                            .commit()
                    }
                    LoadingFragment.TAG -> {
                    }
                }
            }
            Status.SUCCESS -> {
                Timer().schedule(1000) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
            Status.ERROR -> {
            }
        }
    }

    override fun backButtonClick() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_left, R.anim.exit_to_right,
                R.anim.enter_from_right, R.anim.exit_to_left
            )
            .replace(
                R.id.fragment_container_view,
                LoginFragment(),
                LoginFragment.TAG
            )
            .commit()
    }

}
