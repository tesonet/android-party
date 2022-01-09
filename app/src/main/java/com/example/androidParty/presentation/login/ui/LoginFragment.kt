package com.example.androidParty.presentation.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidParty.R
import com.example.androidParty.databinding.FragmentLoginBinding
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.enable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            if (it != null) {
                findNavController().navigate(R.id.serverList)
            }
        })
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.enable(false)
        binding.passwordTxt.addTextChangedListener {
            val userName = binding.usernameTxt.text.toString()
            binding.loginBtn.enable(userName.isNotEmpty() && it.toString().isNotEmpty())
        }
        setObservables()
        binding.loginBtn.setOnClickListener {
            login()
        }
    }

    private fun setObservables() {
        val navController = findNavController()
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    navController.navigate(R.id.serverList)
                }
            }
        })
    }

    private fun login() {
        val userName = binding.usernameTxt.text.toString()
        val password = binding.passwordTxt.text.toString()
        viewModel.login(userName, password)
    }
}