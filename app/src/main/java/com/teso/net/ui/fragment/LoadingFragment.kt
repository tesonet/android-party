package com.teso.net.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teso.net.R
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.vm.LoadingFragmentVM
import com.teso.net.utils.showNewFragment
import com.teso.net.utils.showSnack

class LoadingFragment : BaseFragment() {

    override fun getName(): String = "Loading fragment"

    private lateinit var viewModel: LoadingFragmentVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(LoadingFragmentVM::class.java)
        viewModel.getError().observe(this, Observer { activity?.showSnack(it) })
        viewModel.getNextScreen().observe(this, Observer { openNextScreen() })
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    private fun openNextScreen() {
        activity?.showNewFragment(ServerListFragment::class.java, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchingList()
    }

}