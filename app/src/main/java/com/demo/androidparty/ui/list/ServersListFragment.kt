package com.demo.androidparty.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.demo.androidparty.base.BaseFragment
import com.demo.androidparty.R
import com.demo.androidparty.ui.adapter.ServerListAdapter
import kotlinx.android.synthetic.main.server_list_content.*
import kotlinx.android.synthetic.main.servers_list_fragment.*
import javax.inject.Inject

class ServersListFragment : BaseFragment() {

    override val layoutId: Int = R.layout.servers_list_fragment

    @Inject
    internal lateinit var viewModel: ServersListViewModel
    private lateinit var adapter: ServerListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        setViewListeners()
        observeLiveData()
    }

    private fun setAdapter() {
        adapter = ServerListAdapter()
        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        serverListRecyclerView.addItemDecoration(itemDecorator)
        serverListRecyclerView.adapter = adapter
    }

    private fun setViewListeners() {
        logoutImageView.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun observeLiveData() {
        viewModel.state.observe(::handleState)
        viewModel.logout.observeNullable { logout() }
    }

    private fun handleState(state: ServerListFetchingState) {
        when (state) {
            is ServerListFetchingState.Started -> {
                viewSwitcher.displayedChild = 0
            }
            is ServerListFetchingState.Failed -> {
                showToast(state.reason)
                viewSwitcher.displayedChild = 0
            }
            is ServerListFetchingState.Success -> {
                adapter.setData(state.data)
                viewSwitcher.displayedChild = 1
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        findNavController().navigate(R.id.goToLoginFragment)
    }
}