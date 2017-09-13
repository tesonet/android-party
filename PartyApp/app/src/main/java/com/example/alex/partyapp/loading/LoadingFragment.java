package com.example.alex.partyapp.loading;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.alex.partyapp.BaseFragment;
import com.example.alex.partyapp.R;
import com.example.alex.partyapp.databinding.FragmentLoadingBinding;
import com.example.alex.partyapp.di.App;

import javax.inject.Inject;

public class LoadingFragment extends BaseFragment<FragmentLoadingBinding> {

    @Inject
    LoadingViewModel.Factory factory;
    private LoadingViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loading, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent(this.getContext()).inject(this);
        viewModel = ViewModelProviders.of(this, factory).get(LoadingViewModel.class);
        viewModel.getStatus().observe(this, status -> {
            switch (status) {
                case LOGGED_OUT:
                    navigateToLogin();
                    break;
                case LOADED:
                    navigateToServers();
                    break;
            }
        });

        viewModel.load();

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        binding.imageSpinner.startAnimation(rotateAnimation);
    }
}
