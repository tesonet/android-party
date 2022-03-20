package com.example.feature_server.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.core.ext.viewBinding
import com.example.feature_server.databinding.ActivityServerBinding
import com.example.feature_server.presentation.viewmodel.ServerContract
import com.example.feature_server.presentation.viewmodel.ServerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerActivity : AppCompatActivity() {
    private val viewModel: ServerViewModel by viewModels()
    private val binding: ActivityServerBinding by viewBinding(ActivityServerBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.fragmentContainer.id
        ) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        viewModel.onUiEvent(ServerContract.Event.OnLogoutClicked)
    }
}
