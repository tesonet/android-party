package com.example.alex.partyapp.servers;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.partyapp.BaseFragment;
import com.example.alex.partyapp.R;
import com.example.alex.partyapp.databinding.FragmentServersBinding;
import com.example.alex.partyapp.di.App;

import javax.inject.Inject;

public class ServersFragment extends BaseFragment<FragmentServersBinding> {

    private LinearLayoutManager linearLayoutManager;
    private ServersAdapter listingAdapter;

    @Inject
    ServersViewModel.Factory factory;
    private ServersViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_servers, container, false);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        App.getAppComponent(this.getContext()).inject(this);

        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerServers.setLayoutManager(linearLayoutManager);
        listingAdapter = new ServersAdapter();
        binding.recyclerServers.setAdapter(listingAdapter);

        viewModel = ViewModelProviders.of(this, factory).get(ServersViewModel.class);
        viewModel.getServers().observe(this, servers -> {
            listingAdapter.setData(servers);
            listingAdapter.notifyDataSetChanged();
        });

        viewModel.getStatus().observe(this, status -> {
            switch (status){
                case LOGGED_OUT:
                    navigateToLogin();
                    break;
            }
        });

        viewModel.getError().observe(this, error -> {
            switch (error) {
                case NETWORK_ERROR:
                    showMessage(R.string.network_error);
                    break;
                case FAILED:
                    showMessage(R.string.server_error);
                    break;
                case OFFLINE_RESULTS:
                    showMessage(R.string.offline_data);
                    break;
                case AUTHORIZATION_FAILED:
                    showMessage(R.string.authorization_failed);
            }
        });

        binding.buttonLogout.setOnClickListener(view -> viewModel.logout());
        binding.swipeRefresh.setOnRefreshListener(this::navigateToLoading);
    }
}
