package com.baruckis.androidparty.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.presentation.util.logConsoleVerbose
import com.baruckis.androidparty.ui.R
import com.baruckis.androidparty.ui.callback.BackCallback
import com.baruckis.androidparty.ui.main.MainActivity
import dagger.android.AndroidInjection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

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

        loginViewModel.serversResource.observe(this, Observer { resource ->
            handleServersListPresentationResourceStatus(resource)
        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, LoginFragment(), LoginFragment.TAG)
                .commit()
        }

    }

    private fun handleLoginPresentationResourceStatus(
        dataResource: Resource<LoginPresentation>,
        delayTime: Long = DELAY
    ) {

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

                GlobalScope.launch {
                    delay(delayTime)
                    loginViewModel.fetchServersRemotely()
                }

            }
            Status.ERROR -> {
            }
        }
    }

    private fun handleServersListPresentationResourceStatus(dataResource: Resource<List<ServerPresentation>>) {

        when (dataResource.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_msg_welcome, loginViewModel.username),
                    Toast.LENGTH_LONG
                ).show()
                logConsoleVerbose(dataResource.data.toString())
                finish()
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

    companion object {
        private const val DELAY: Long = 1500
    }

}
