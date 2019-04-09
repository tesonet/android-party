package com.edvinas.balkaitis.party.servers.fragment

import android.os.Bundle
import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.base.BaseDaggerFragment
import com.edvinas.balkaitis.party.servers.network.Server

class ServersFragment : BaseDaggerFragment() {
    override fun getLayoutId() = R.layout.fragment_servers

    companion object {
        private const val KEY_SERVERS = "key.servers"
        fun newInstance(servers: List<Server>): ServersFragment {
            return ServersFragment().apply {
                arguments = Bundle().apply { putParcelableArray(KEY_SERVERS, servers.toTypedArray()) }
            }
        }
    }
}
