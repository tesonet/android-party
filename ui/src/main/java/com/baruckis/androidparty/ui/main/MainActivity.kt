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

package com.baruckis.androidparty.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baruckis.androidparty.presentation.main.MainViewModel
import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.ui.R
import com.baruckis.androidparty.ui.databinding.ActivityMainBinding
import com.baruckis.androidparty.ui.login.LoginActivity
import com.baruckis.androidparty.ui.mapper.ServerUiMapper
import com.baruckis.androidparty.ui.util.onActionButtonClick
import com.baruckis.androidparty.ui.util.showSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var serversListRecyclerViewAdapter: ServersListRecyclerViewAdapter

    @Inject
    lateinit var serverUiMapper: ServerUiMapper

    private var snackbarRemoteFetchError: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = mainViewModel
        }

        binding.customToolbar.logout.setOnClickListener {
            mainViewModel.logoutClick()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            snackbarRemoteFetchError?.dismiss()
            mainViewModel.fetchServersRemotely()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = serversListRecyclerViewAdapter

        setContentView(binding.root)

        mainViewModel.serversResource.observe(this, Observer { resource ->
            handlePresentationResourceStatus(resource)
        })

        mainViewModel.fetchServersLocally()

        val requestRemoteFetchError: Boolean =
            intent.extras?.getBoolean(REQUEST_REMOTE_FETCH_ERROR, false) ?: false

        if (requestRemoteFetchError) {
            showFetchingServersError()
        }

    }

    override fun onDestroy() {
        snackbarRemoteFetchError?.dismiss()
        super.onDestroy()
    }

    private fun showFetchingServersError() {
        binding.swipeRefreshLayout.isRefreshing = false
        snackbarRemoteFetchError = binding.coordinatorLayout.showSnackbar(
            getString(R.string.error_msg_fetching_list),
            Snackbar.LENGTH_INDEFINITE
        ) {
            onActionButtonClick(getString(R.string.error_action_retry)) {
                this.dismiss()
                binding.swipeRefreshLayout.isRefreshing = true
                mainViewModel.fetchServersRemotely()
            }
        }
    }


    private fun handlePresentationResourceStatus(dataResource: Resource<List<ServerPresentation>>) {

        when (dataResource.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {

                val serversListUi =
                    dataResource.data?.map { serverUiMapper.mapToUi(it) } ?: emptyList()

                serversListRecyclerViewAdapter.setData(serversListUi)

                binding.swipeRefreshLayout.isRefreshing = false
            }
            Status.ERROR -> {
                showFetchingServersError()
            }

        }

    }

    companion object {
        const val REQUEST_REMOTE_FETCH_ERROR = "request_remote_fetch_error"
    }

}