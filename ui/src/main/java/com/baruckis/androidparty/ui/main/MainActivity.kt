package com.baruckis.androidparty.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.main.MainViewModel
import com.baruckis.androidparty.ui.databinding.ActivityMainBinding
import com.baruckis.androidparty.ui.login.LoginActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

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

        setContentView(binding.root)
    }

}