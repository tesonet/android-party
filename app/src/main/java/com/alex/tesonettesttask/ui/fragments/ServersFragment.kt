package com.alex.tesonettesttask.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.TesonetApplication
import com.alex.tesonettesttask.logic.utils.presentFragment
import com.alex.tesonettesttask.ui.adapters.ServersAdapter
import com.alex.tesonettesttask.ui.presenters.ServerPresenter
import kotlinx.android.synthetic.main.fragment_servers.*
import javax.inject.Inject

class ServersFragment : Fragment() {

    @Inject
    lateinit var serverPresenter: ServerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        TesonetApplication.instance.mainComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_servers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val servers = serverPresenter.getCachedServers()
        serverList.adapter = ServersAdapter(servers)
        serverList.layoutManager = LinearLayoutManager(context)
        logoutButton.setOnClickListener {
            serverPresenter.logout()
            activity?.presentFragment(LoginFragment())
        }
    }


}
