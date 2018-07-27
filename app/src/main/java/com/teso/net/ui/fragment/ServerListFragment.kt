package com.teso.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.ui.adapters.ServerAdapter
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.ServerListVM
import com.teso.net.utils.DividerItemDecoration
import com.teso.net.utils.onClick
import com.teso.net.utils.showNewFragment
import com.teso.net.utils.showSnack
import kotlinx.android.synthetic.main.fragment_server.*
import timber.log.Timber


class ServerListFragment : BaseFragment() {

    override fun getName(): String = "Server list fragment"

    private lateinit var viewModel: ServerListVM
    private val listSites: ArrayList<ServerEntity> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ServerListVM::class.java)
        viewModel.getListOfServers().observe(this, Observer { servers -> servers?.let { showList(servers) } })
        return inflater.inflate(R.layout.fragment_server, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { serverList.addItemDecoration(DividerItemDecoration(it)) }
        serverList.adapter = ServerAdapter(listSites, { onClickLog(it) })
        listLogout.onClick { if (isClickAllowed()) logout() }
    }

    private fun logout() {
        viewModel.logout()
        activity?.showNewFragment(LoginFragment::class.java, false)
    }

    private fun showList(sites: List<ServerEntity>) {
        Timber.d("Get site list in UI ${sites.size}")
        listSites.clear()
        listSites.addAll(sites)
        serverList.adapter.notifyDataSetChanged()
    }

    private fun onClickLog(server: ServerEntity) {
        Timber.d("On click was fired ")
        activity?.showSnack(ErrorModel(string = "ServerEntity ${server.name} was chosen"))
    }
}