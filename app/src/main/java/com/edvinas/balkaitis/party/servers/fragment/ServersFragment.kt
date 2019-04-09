package com.edvinas.balkaitis.party.servers.fragment

import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.base.BaseDaggerFragment

class ServersFragment : BaseDaggerFragment() {
    override fun getLayoutId() = R.layout.fragment_servers

    companion object {
        fun newInstance() = ServersFragment()
    }
}
