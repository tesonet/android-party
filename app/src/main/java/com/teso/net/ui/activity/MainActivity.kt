package com.teso.net.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.teso.net.R
import com.teso.net.ui.base.BaseActivity
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.MainActivityVM
import com.teso.net.utils.changeStatusBarColor
import com.teso.net.utils.showNewFragment


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(R.color.colorDarkBlue)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        viewModel.getNextScreen().observe(this, Observer { openNextScreen(it) })
    }

    private fun openNextScreen(fragment: Class<out BaseFragment>?) {
        fragment?.let {
            showNewFragment(it, false)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkIfUserAlreadyLogin()
    }
}