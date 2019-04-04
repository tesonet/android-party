package com.edvinas.balkaitis.party.login

import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.base.BaseDaggerFragment

class LoginFragment : BaseDaggerFragment() {
    override fun getLayoutId() = R.layout.fragment_login

    companion object {
        fun newInstance() = LoginFragment()
    }
}
