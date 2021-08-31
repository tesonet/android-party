package com.tesonet.testio.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tesonet.testio.databinding.LoadingFragmentBinding
import com.tesonet.testio.ui.login.LoginFragment
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
        viewModel.savedServers.observeIt(this) {
            if (it == true) {
                // TODO Navigate to servers view
            }
        }
    }
}