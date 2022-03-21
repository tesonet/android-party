package com.example.featureServer.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.core.delegate.viewBinding
import com.example.core.ext.asVerticalLayout
import com.example.core.ext.doNotLeak
import com.example.featureServer.R
import com.example.featureServer.databinding.FragmentServerBinding
import com.example.featureServer.presentation.view.adapter.ServerListAdapter
import com.example.featureServer.presentation.viewmodel.ServerContract
import com.example.featureServer.presentation.viewmodel.ServerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ServerFragment : Fragment(R.layout.fragment_server) {
    private val binding: FragmentServerBinding by viewBinding(FragmentServerBinding::bind)
    private val viewModel: ServerViewModel by activityViewModels()

    @Inject
    lateinit var adapter: ServerListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewState()
    }

    private fun initView() {
        binding.list.run {
            asVerticalLayout()
            adapter = this@ServerFragment.adapter
            doNotLeak(this@ServerFragment)
        }

        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        binding.list.addItemDecoration(itemDecorator)

        binding.logoutButton.setOnClickListener {
            viewModel.onUiEvent(ServerContract.Event.OnLogoutClicked)
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    adapter.items = viewState.serverList
                }
            }
        }
    }
}
