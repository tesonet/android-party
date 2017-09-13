package com.example.alex.partyapp.login;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.partyapp.BaseFragment;
import com.example.alex.partyapp.BuildConfig;
import com.example.alex.partyapp.R;
import com.example.alex.partyapp.databinding.FragmentLoginBinding;
import com.example.alex.partyapp.di.App;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Inject
    LoginViewModel.Factory factory;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent(this.getContext()).inject(this);
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        viewModel.getStatus().observe(this, loginStatus -> {
            switch (loginStatus){
                case LOGGING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case LOGGED_IN:
                    navigateToLoading();
                default:
                    binding.progressBar.setVisibility(View.INVISIBLE);
            }
        });
        viewModel.getError().observe(this, error -> {
            switch (error) {
                case FAILED:
                    showMessage(R.string.server_error);
                    break;
                case NETWORK_ERROR:
                    showMessage(R.string.network_error);
                    break;
                case AUTHORIZATION_FAILED:
                    showMessage(R.string.authorization_failed);
                    break;
            }
        });

        binding.buttonLogin.setOnClickListener(view -> viewModel.login(
                binding.editUsername.getText().toString(),
                binding.editPassword.getText().toString()));

        if (BuildConfig.DEBUG) {
            binding.editUsername.setText("tesonet");
            binding.editPassword.setText("partyanimal");
        }
    }
}
