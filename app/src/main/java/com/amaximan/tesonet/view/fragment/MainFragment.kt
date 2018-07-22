package com.amaximan.tesonet.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.amaximan.tesonet.R
import com.amaximan.tesonet.databinding.FragmentMainBinding
import com.amaximan.tesonet.model.sp.SPManager
import com.amaximan.tesonet.other.adapters.rv.ServersRVAdapter
import com.amaximan.tesonet.other.extensions.loadFragment
import com.amaximan.tesonet.view.base.BaseFragment
import com.amaximan.tesonet.viewModel.MainVM
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutResId() = R.layout.fragment_main

    private val rvAdapter = ServersRVAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding?.viewModel = ViewModelProviders.of(this).get(MainVM::class.java)
        initRV()

        initClicks()
    }

    private fun initRV() {
        rv_main.layoutManager = LinearLayoutManager(context)
        rv_main.adapter = rvAdapter

        fragmentBinding?.viewModel?.servers?.observe(this, android.arch.lifecycle.Observer {
            it?.toList()?.let {
                rvAdapter.setData(it)
            }
        })
    }

    private fun initClicks() {
        iv_logout.setOnClickListener {
            SPManager.instance.deleteToken()
            activity?.loadFragment(AuthFragment(), false)
        }
    }
}