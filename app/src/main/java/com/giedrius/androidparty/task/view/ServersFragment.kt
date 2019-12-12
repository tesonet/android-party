package com.giedrius.androidparty.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.giedrius.androidparty.R
import com.giedrius.androidparty.task.adapter.ServerAdapter
import com.giedrius.androidparty.task.navigation.NavigationListener
import com.giedrius.androidparty.task.viewmodel.ServersListViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_servers.view.*
import javax.inject.Inject

class ServersFragment(val navListener: NavigationListener): DaggerFragment() {

    @Inject lateinit var serversViewModel: ServersListViewModel
    lateinit var serversAdapter: ServerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_servers, container, false)
        view.server_list_recycler.layoutManager = LinearLayoutManager(activity)
        serversAdapter = ServerAdapter(listOf())
        view.server_list_recycler.adapter = serversAdapter
        serversViewModel.getServers()
        view.server_list_recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        view.logout_button.setOnClickListener { navListener.onLogoutPerformed() }
        createServersObserver()
        return view
    }

    private fun createServersObserver() {
        serversViewModel.servers.observe(this, Observer {
            serversAdapter.setData(it.sortedBy { it.distance })
            serversAdapter.notifyDataSetChanged()
        })
    }

    companion object {
        @JvmStatic fun newInstance(navlistener: NavigationListener) = ServersFragment(navlistener).apply{}
    }
}
