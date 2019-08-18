package com.example.androidparty.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.androidparty.R
import com.example.androidparty.databinding.FragmentLoginBinding
import com.example.androidparty.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    lateinit var binder: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binder.root
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
            }
    }
}
