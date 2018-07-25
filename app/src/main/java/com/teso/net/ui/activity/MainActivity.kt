package com.teso.net.ui.activity

import android.os.Bundle
import com.teso.net.R
import com.teso.net.ui.base.BaseActivity
import com.teso.net.ui.fragment.LoginFragment


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        LoginFragment().show(supportFragmentManager, false)
    }
}