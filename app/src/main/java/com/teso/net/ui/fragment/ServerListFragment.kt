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
import com.teso.net.ui.adapters.SiteAdapter
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.SiteFragmentVM
import com.teso.net.utils.showSnack
import kotlinx.android.synthetic.main.fragment_server.*
import timber.log.Timber


class ServerListFragment : BaseFragment() {

    override fun getName(): String = "Server list fragment"

    private lateinit var viewModel: SiteFragmentVM
    private val listSites: ArrayList<ServerEntity> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(SiteFragmentVM::class.java)
        viewModel.getListOfSites().observe(this, Observer { sites -> sites?.let { showList(sites) } })
        return inflater.inflate(R.layout.fragment_server, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serverList.adapter = SiteAdapter(listSites, { onClickLog(it) })
    }

    private fun showList(sites: List<ServerEntity>) {
        Timber.d("Get site list in UI")
        listSites.clear()
        listSites.addAll(sites)
        serverList.adapter.notifyDataSetChanged()
    }

    private fun onClickLog(server: ServerEntity) {
        Timber.d("On click was fired ")
        activity?.showSnack(ErrorModel(string = "ServerEntity ${server.name} was chosen"))
    }
}