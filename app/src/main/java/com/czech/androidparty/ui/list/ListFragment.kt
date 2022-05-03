package com.czech.androidparty.ui.list

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.czech.androidparty.connection.NetworkConnection
import com.czech.androidparty.databinding.ListFragmentBinding
import com.czech.androidparty.responseStates.ListState
import com.czech.androidparty.ui.list.adapter.DataListAdapter
import com.czech.androidparty.ui.list.adapter.DataListDiffCallback
import com.czech.androidparty.utils.hide
import com.czech.androidparty.utils.show
import com.czech.androidparty.utils.showErrorDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ListViewModel>()

    private val dataListAdapter by lazy { DataListAdapter(DataListDiffCallback) }

    private lateinit var networkConnection: NetworkConnection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNetwork()
        observe()

        binding.dataList.apply {
            adapter = dataListAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeNetwork() {
        networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (!isConnected) {
                viewModel.getDataFromDB()
            } else {
                viewModel.getDataWithNetwork()
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.listState.collect {
                when (it) {
                    is ListState.Loading -> {
                        binding.dataList.hide()
                        binding.loader.loadingView.show()
                        run { delay(2000) }
                    }
                    is ListState.Error -> {
                        binding.dataList.show()
                        binding.loader.loadingView.hide()
                        it.message.let { message ->
                            requireActivity().showErrorDialog(message)
                        }
                    }
                    is ListState.Success -> {
                        binding.dataList.show()
                        binding.loader.loadingView.hide()

                        if (it.data.isNullOrEmpty()) {
                            requireActivity().showErrorDialog("no saved data to display")
                        } else {
                            dataListAdapter.submitList(it.data)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}