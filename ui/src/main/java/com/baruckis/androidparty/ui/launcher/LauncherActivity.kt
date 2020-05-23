package com.baruckis.androidparty.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.launcher.LaunchDestination
import com.baruckis.androidparty.presentation.launcher.LauncherViewModel
import com.baruckis.androidparty.presentation.util.checkAllMatched
import com.baruckis.androidparty.ui.login.LoginActivity
import com.baruckis.androidparty.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * A 'Trampoline' activity for sending users to an appropriate screen on launch.
 */
class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: LauncherViewModel =
            ViewModelProvider(this, viewModelFactory).get(LauncherViewModel::class.java)

        viewModel.launchDestinationLiveData.observe(this, Observer {
            // Only proceed if the event has never been handled.
            it.getContentIfNotHandled()?.let { destination ->
                when (destination) {
                    LaunchDestination.MAIN_ACTIVITY -> startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    LaunchDestination.LOGIN -> startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                }.checkAllMatched
                finish()
            }
        }
        )
    }
}