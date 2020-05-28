/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        val viewModel =
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