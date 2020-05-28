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
import com.baruckis.androidparty.ui.databinding.ActivityMainBinding
import com.baruckis.androidparty.ui.login.LoginActivity
import com.baruckis.androidparty.ui.mapper.ServerUiMapper
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

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = serversListRecyclerViewAdapter

        setContentView(binding.root)

        mainViewModel.serversResource.observe(this, Observer { resource ->
            handlePresentationResourceStatus(resource)
        })

        mainViewModel.fetchServersLocally()
    }


    private fun handlePresentationResourceStatus(dataResource: Resource<List<ServerPresentation>>) {


        when (dataResource.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {

                val venuesUi = dataResource.data?.map { serverUiMapper.mapToUi(it) } ?: emptyList()

                serversListRecyclerViewAdapter.setData(venuesUi)

            }
            Status.ERROR -> {

            }

        }

    }

}