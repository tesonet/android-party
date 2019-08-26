package com.example.androidparty.view

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.androidparty.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), FragmentsNavigationListener {

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

    override fun onLoginClicked() {
        val serverListFragment = ServerListFragment.newInstance(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, serverListFragment)
        fragmentTransaction.commit()
    }

    override fun onLogoutClicked() {
        val loginFragment = LoginFragment.newInstance(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, loginFragment)
        fragmentTransaction.commit()
    }

}
