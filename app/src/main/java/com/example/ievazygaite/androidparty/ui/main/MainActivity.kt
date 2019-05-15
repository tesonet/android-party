package com.example.ievazygaite.androidparty.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.ievazygaite.androidparty.R
import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.di.component.DaggerActivityComponent
import com.example.ievazygaite.androidparty.di.module.ActivityModule
import com.example.ievazygaite.androidparty.ui.list.ServerListFragment
import com.example.ievazygaite.androidparty.ui.login.LoginFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        init()
        showLoginFragment()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    fun init() {
        presenter.attach(this)
    }

    override fun showLoginFragment() {
        if (supportFragmentManager.findFragmentByTag(LoginFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.frame, LoginFragment().newInstance(), LoginFragment.TAG)
                .commit()
        }
    }

    override fun showListFragment(servers: List<Server>) {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            .replace(R.id.frame, ServerListFragment().newInstance(servers), ServerListFragment.TAG)
            .commit()
    }
}