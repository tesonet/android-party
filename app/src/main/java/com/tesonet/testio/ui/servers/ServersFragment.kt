package com.tesonet.testio.ui.servers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.tesonet.testio.R
import com.tesonet.testio.databinding.ServersFragmentBinding
import com.tesonet.testio.ui.adapters.ServersAdapter
import com.tesonet.testio.utils.LoginHelper
import com.tesonet.testio.utils.Resource.Complete
import com.tesonet.testio.utils.observeIt
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ServersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ServersViewModel

    private lateinit var binding: ServersFragmentBinding
    private lateinit var serversAdapter: ServersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ServersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observerListOfServers()

        binding.imageButtonLogOut.setOnClickListener {
            deleteUserAndServers()
        }
    }

    private fun observerListOfServers() {
        viewModel.servers.observeIt(this) { servers ->
            when (servers) {
                !is Complete -> { }
                else -> {
                    serversAdapter.setItem(servers.value)
                }
            }
        }
    }

    private fun deleteUserAndServers() {
        viewModel.deleteAllServers()
        LoginHelper(requireContext()).logOut()
        findNavController().navigate(R.id.navigation_serversFragment_to_loginFragment)
    }

    private fun initRecyclerView() {
        serversAdapter = ServersAdapter(requireContext())
        val decorator = DividerItemDecoration(requireContext(), VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)

        binding.recyclerViewServers.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = serversAdapter
            addItemDecoration(decorator)
        }

        serversAdapter.selectedServer = { server ->
            Toast.makeText(requireContext(), getString(R.string.selected_server, server.name), Toast.LENGTH_SHORT)
                .show()
        }
    }
}