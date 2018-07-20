package com.amaximan.tesonet.view.activity

import android.os.Bundle
import com.amaximan.tesonet.R
import com.amaximan.tesonet.other.extensions.loadFragment
import com.amaximan.tesonet.view.base.BaseActivity
import com.amaximan.tesonet.view.fragment.AuthFragment

class MainActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(AuthFragment(), false)
    }
}
