package com.example.featureServer.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.core.R.string
import com.example.core.delegate.viewBinding
import com.example.core.ext.exhaustive
import com.example.core.ext.safeNavigate
import com.example.featureServer.R
import com.example.featureServer.databinding.FragmentLoadingBinding
import com.example.featureServer.presentation.viewmodel.ServerContract
import com.example.featureServer.presentation.viewmodel.ServerViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadFragment : Fragment(R.layout.fragment_loading) {
    private val binding: FragmentLoadingBinding by viewBinding(FragmentLoadingBinding::bind)
    private val viewModel: ServerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onUiEvent(ServerContract.Event.OnLoadingOpened)
        observeEffect()
    }

    private fun observeEffect() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is ServerContract.Effect.NetworkErrorEffect -> {
                            showSnackBar(getString(string.network_error))
                        }
                        is ServerContract.Effect.UnknownErrorEffect -> {
                            showSnackBar(getString(string.unknown_error))
                        }
                        is ServerContract.Effect.OnNavigationEffect -> {
                            navigationToServerList()
                        }
                        else -> {
                            // do nothing
                        }
                    }.exhaustive
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.container, text, Snackbar.LENGTH_LONG).show()
    }

    private fun navigationToServerList() {
        findNavController().safeNavigate(LoadFragmentDirections.actionLoadFragmentToServerFragment())
    }
}
