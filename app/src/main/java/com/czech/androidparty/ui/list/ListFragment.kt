package com.czech.androidparty.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.czech.androidparty.R
import com.czech.androidparty.databinding.ListFragmentBinding
import com.czech.androidparty.databinding.LoginFragmentBinding
import com.czech.androidparty.responseStates.ListState
import com.czech.androidparty.ui.login.LoginViewModel
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.dataList.apply {
            adapter = dataListAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getData()
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

                        dataListAdapter.submitList(it.data)
                    }
                    else -> {}
                }
            }
        }
    }


}