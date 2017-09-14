package com.example.alex.partyapp.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.alex.partyapp.data.Constants;
import com.example.alex.partyapp.data.ServersRepository;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private ServersRepository repository;

    public LoginViewModel(ServersRepository repository){
        this.repository = repository;
    }

    public LiveData<Constants.Status> getStatus() {
        return repository.getStatus();
    }

    public MutableLiveData<Constants.Error> getError() {
        return repository.getError();
    }

    public void login(String username, String password) {
        repository.login(username, password);
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final ServersRepository repository;

        @Inject
        public Factory(ServersRepository repository){
            this.repository = repository;
        }

        @Override
        public LoginViewModel create(Class modelClass) {
            return new LoginViewModel(repository);
        }
    }

}
