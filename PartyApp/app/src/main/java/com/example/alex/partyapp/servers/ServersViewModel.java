package com.example.alex.partyapp.servers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.alex.partyapp.data.Constants;
import com.example.alex.partyapp.data.ServersRepository;
import com.example.alex.partyapp.data.Server;

import java.util.List;

import javax.inject.Inject;


public class ServersViewModel extends ViewModel {

    private ServersRepository repository;

    public ServersViewModel(ServersRepository repository) {
        this.repository = repository;
    }

    public LiveData<Constants.Status> getStatus() {
        return repository.getStatus();
    }

    public MutableLiveData<Constants.Error> getError() {
        return repository.getError();
    }

    public LiveData<List<Server>> getServers(){
        return repository.getServers();
    }

    public void logout() {
        repository.logout();
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final ServersRepository repository;

        @Inject
        public Factory(ServersRepository repository) {
            this.repository = repository;
        }

        @Override
        public ServersViewModel create(Class modelClass) {
            return new ServersViewModel(repository);
        }
    }
}

