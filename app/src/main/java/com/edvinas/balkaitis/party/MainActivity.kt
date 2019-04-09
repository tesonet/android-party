package com.edvinas.balkaitis.party

import android.os.Bundle
import com.edvinas.balkaitis.party.base.BaseDaggerActivity
import com.edvinas.balkaitis.party.login.fragment.LoginFragment
import com.edvinas.balkaitis.party.utils.extensions.replaceFragment

class MainActivity : BaseDaggerActivity() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(LoginFragment.newInstance())
        }
    }
}
