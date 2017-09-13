package com.example.alex.partyapp.loading;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.alex.partyapp.data.Constants;
import com.example.alex.partyapp.data.ServersRepository;

import javax.inject.Inject;

public class LoadingViewModel extends ViewModel {

    private ServersRepository repository;

    public LoadingViewModel(ServersRepository repository){
        this.repository = repository;
    }

    public LiveData<Constants.Status> getStatus() {
        return repository.getStatus();
    }

    public void load(){
        repository.load();
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final ServersRepository repository;

        @Inject
        public Factory(ServersRepository repository){
            this.repository = repository;
        }

        @Override
        public LoadingViewModel create(Class modelClass) {
            return new LoadingViewModel(repository);
        }
    }

}
