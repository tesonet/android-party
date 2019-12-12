package com.giedrius.androidparty.task.view

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.giedrius.androidparty.R
import com.giedrius.androidparty.task.navigation.NavigationListener
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavigationListener {

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = this.supportFragmentManager
        val loginFragment = LoginFragment.newInstance(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, loginFragment)
        fragmentTransaction.commit()
    }

    override fun onLoginPerformed() {
        val serversFragment = ServersFragment.newInstance(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, serversFragment)
        fragmentTransaction.commit()
    }

    override fun onLogoutPerformed() {
        val loginFragment = LoginFragment.newInstance(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, loginFragment)
        fragmentTransaction.commit()
    }
}
