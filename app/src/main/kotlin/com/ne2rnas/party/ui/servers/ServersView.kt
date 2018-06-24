package com.ne2rnas.party.ui.servers

import com.ne2rnas.party.base.BaseView
import com.ne2rnas.party.data.servers.Server

interface ServersView : BaseView {
    fun showError(error: String?)

    fun showLoading()

    fun hideLoading()

    fun showAdapter()

    fun hideAdapter()

    fun updateServers(servers: List<Server>)

    fun logout()
}
