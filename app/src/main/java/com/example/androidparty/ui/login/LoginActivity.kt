package com.example.androidparty.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidparty.R
import com.example.androidparty.SharedPreferences
import com.example.androidparty.utils.UserData
import com.example.androidparty.utils.isConnectedToInternet
import com.example.androidparty.utils.openLoadingActivity
import kotlinx.android.synthetic.main.login_layout.*
import retrofit2.HttpException
import timber.log.Timber

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(LoginViewModel::class.java)
        val sharedPreferences = SharedPreferences(applicationContext)
        if (!sharedPreferences.getToken().isNullOrEmpty()) {
            openLoadingActivity(this)
            this.finish()
        }
        viewButtonLogIn.setOnClickListener(this)
    }

    @SuppressLint("CheckResult")
    private fun observeViewModel(viewModel: LoginViewModel) {
        viewModel.getLoginObservable().subscribe({
            openLoadingActivity(this)
        }, { t: Throwable? ->
            Timber.e(t)
            if((t as HttpException).message() == "Unauthorized") {
                Toast.makeText(this, getString(R.string.check_login_credentials), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            viewButtonLogIn.id -> {
                val userData = UserData(
                    viewEditTextUsername.text.toString(),
                    viewEditTextPassword.text.toString()
                )
                if (isConnectedToInternet(this)) {
                    viewModel.login(userData)
                } else {
                    Toast.makeText(this, getString(R.string.check_internet), Toast.LENGTH_SHORT).show()
                }
                observeViewModel(viewModel)
            }
        }
    }
}
