package com.tesonet.testio.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tesonet.testio.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIfUserIsLoggedIn()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private fun checkIfUserIsLoggedIn() {
        this.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).let {
            val isLoggedIn = it.getBoolean(LOG_IN_STATE, false)
            if (!isLoggedIn) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.loadingFragment) // TODO Change with server list fragment
            }
        }
    }

    companion object {

        const val SHARED_PREFERENCES_FILE_NAME: String = "testio_shared"
        const val LOG_IN_STATE: String = "log_in_state"
    }
}