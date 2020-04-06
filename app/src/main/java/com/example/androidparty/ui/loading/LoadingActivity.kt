package com.example.androidparty.ui.loading

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidparty.R
import com.example.androidparty.SharedPreferences
import com.example.androidparty.utils.isConnectedToInternet
import com.example.androidparty.utils.openLoginScreen
import com.example.androidparty.utils.openServerListScreen
import timber.log.Timber

class LoadingActivity : AppCompatActivity() {

    lateinit var viewModel: LoadingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        val sharedPreferences = SharedPreferences(applicationContext)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(LoadingViewModel::class.java)
        val token = sharedPreferences.getToken()
        if (!token.isNullOrEmpty()) {
            viewModel.getServers(
                token,
                isConnectedToInternet(this)
            )
        } else {
            openLoginScreen(this)
            this.finish()
        }
        observeViewModel(viewModel)
    }

    @SuppressLint("CheckResult")
    private fun observeViewModel(viewModel: LoadingViewModel) {
        viewModel.getServersObservable()
            .subscribe({ result ->
                openServerListScreen(this, result)
                this.finish()
            }, { t: Throwable? ->
                Timber.e(t)
                Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_SHORT).show()
            })
    }
}