/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.ui.R
import com.baruckis.androidparty.ui.callback.BackCallback
import com.baruckis.androidparty.ui.databinding.FragmentLoadingBinding
import com.baruckis.androidparty.ui.mapper.LoginUiMapper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoadingFragment(internal var callback: BackCallback) : DaggerFragment() {

    companion object {
        const val TAG = "loading_fragment_tag"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var loginUiMapper: LoginUiMapper

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: FragmentLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.let { activity ->

            loginViewModel =
                ViewModelProvider(activity, viewModelFactory).get(LoginViewModel::class.java)

            binding = FragmentLoadingBinding.inflate(inflater, container, false).apply {
                viewModel = loginViewModel
            }

            loginViewModel.loginResource.observe(activity, Observer { resource ->
                binding.stateResource = resource
                handleLoginPresentationResourceStatus(resource)
            })

            loginViewModel.serversResource.observe(activity, Observer { resource ->
                binding.stateResource = resource
                handleServersListPresentationResourceStatus(resource)
            })

            binding.backCallback = object : BackCallback {
                override fun backButtonClick() {
                    callback.backButtonClick()
                }
            }

        }

        return binding.root
    }

    private fun handleLoginPresentationResourceStatus(dataResource: Resource<LoginPresentation>) {

        when (dataResource.status) {
            Status.LOADING -> {
                binding.statusMessage.text = getString(R.string.status_msg_logging_in)
            }
            Status.SUCCESS -> {
                binding.statusMessage.text =
                    getString(R.string.status_msg_logged_in_as,
                        dataResource.data?.let { loginUiMapper.mapToUi(it).username })
            }
            Status.ERROR -> {
                binding.statusMessage.text = getString(R.string.status_msg_error)
            }
        }
    }

    private fun handleServersListPresentationResourceStatus(dataResource: Resource<List<ServerPresentation>>) {

        when (dataResource.status) {
            Status.LOADING -> {
                binding.statusMessage.text = getString(R.string.status_msg_fetching_list)
            }
            Status.SUCCESS -> {

            }
            Status.ERROR -> {

            }
        }
    }

}