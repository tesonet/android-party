package com.example.androidParty.presentation.serverList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidParty.R
import com.example.androidParty.databinding.FragmentServerListBinding
import com.example.androidParty.datalayer.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServerListFragment : Fragment() {
  private lateinit var binding: FragmentServerListBinding
  private lateinit var adapter: ServerListAdapter

  private val viewModel: ServerListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentServerListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.logoutImageView.setOnClickListener {
      logout()
    }
    setRecycleView()
    setObservables()
  }

  private fun setObservables() {
    viewModel.listServer.observe(viewLifecycleOwner, {
      when (it) {
        is Resource.Error -> {
          Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
        is Resource.Loading -> {
          binding.progressBarContainer.setBackgroundResource(R.drawable.bg)
          binding.progressBarContainer.visibility = View.VISIBLE
          binding.serverPage.visibility = View.INVISIBLE
        }
        is Resource.Success -> {
          binding.progressBarContainer.visibility = View.INVISIBLE
          binding.serverPage.visibility = View.VISIBLE
          adapter.submitList(it.data)
        }
      }
    })
  }

  private fun setRecycleView() {
    val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    adapter = ServerListAdapter()
    binding.recyclerView.layoutManager = manager
    binding.recyclerView.adapter = adapter
  }

  private fun logout() {
    lifecycleScope.launch {
      viewModel.logoutBtn()
      findNavController().navigate(R.id.loginFragment)
    }
  }
}