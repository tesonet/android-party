package com.tesonet.testio.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tesonet.testio.R
import com.tesonet.testio.databinding.LoadingFragmentBinding
import com.tesonet.testio.ui.login.LoginFragment
import com.tesonet.testio.utils.LoginHelper
import com.tesonet.testio.utils.Resource.Complete
import com.tesonet.testio.utils.Resource.Error
import com.tesonet.testio.utils.observeIt
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoadingFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: LoadingViewModel

    private lateinit var binding: LoadingFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoadingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle? = this.arguments
        bundle?.let {
            it.getString(LoginFragment.REQUEST_TOKEN)?.let { requestToken ->
                viewModel.fetchServers(requestToken)
            }
        }

        observeServers()
    }

    private fun observeServers() {
        viewModel.savedServers.observeIt(this) { state ->
            when (state) {
                is Complete -> {
                    if (state.value) {
                        findNavController().navigate(R.id.navigation_loadingFragment_to_serversFragment)
                    }
                }
                is Error -> {
                    Toast.makeText(context, getString(R.string.failed_fetching_servers, state.error), Toast.LENGTH_SHORT).show()
                    resetServersFetchingFlow()
                }
                else -> {
                    // no action needed
                }
            }
        }
    }

    private fun resetServersFetchingFlow() {
        viewModel.resetSavedServerState()
        val loginHelper = LoginHelper(requireContext())
        if (loginHelper.isLoggedIn()) {
            loginHelper.logOut()
            findNavController().navigateUp()
        }
    }
}