package assignment.tesonet.homework.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import assignment.tesonet.homework.App
import assignment.tesonet.homework.R
import assignment.tesonet.homework.ServerResponse
import assignment.tesonet.homework.databinding.ActivityLoginBinding
import assignment.tesonet.homework.storage.AppPreferences
import assignment.tesonet.homework.ui.viewmodel.LoginViewModel
import assignment.tesonet.homework.ui.factory.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = AppPreferences.token

        if (token != null && !token.isNullOrBlank()) {
            skip()
        }

        viewModel = ViewModelProviders.of(this, LoginViewModelFactory((application as App).service)).get(LoginViewModel::class.java)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.apply {
            loginViewModel = viewModel
            setLifecycleOwner(this@LoginActivity)
        }

        viewModel.loginResponse.observe(this, Observer {
            when (it) {
                ServerResponse.SUCCESS -> {
                    viewModel.token?.let {
                        AppPreferences.token = it
                        skip()
                    }
                }
                ServerResponse.ERROR -> {
                    Toast.makeText(applicationContext, getString(R.string.response_error), Toast.LENGTH_SHORT).show()
                }
                ServerResponse.NO_INTERNET -> {
                    Toast.makeText(applicationContext, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @Suppress("UNUSED_PARAMETER")
    fun onClickLogin(view: View) {
        viewModel.login(username.text.toString(), password.text.toString())
    }

    private fun skip() {
        startActivity(ServerListActivity.newIntent(applicationContext))
        finish()
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}