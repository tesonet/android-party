package com.edvinas.balkaitis.party

import android.os.Bundle
import com.edvinas.balkaitis.party.base.BaseDaggerActivity
import com.edvinas.balkaitis.party.login.fragment.LoginFragment
import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.fragment.ServersFragment
import com.edvinas.balkaitis.party.utils.extensions.replaceFragment
import javax.inject.Inject

class MainActivity : BaseDaggerActivity() {
    @Inject lateinit var tokenStorage: TokenStorage

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            if (tokenStorage.getToken().isEmpty()) {
                replaceFragment(LoginFragment.newInstance())
            } else {
                replaceFragment(ServersFragment.newInstance())
            }
        }
    }
}
