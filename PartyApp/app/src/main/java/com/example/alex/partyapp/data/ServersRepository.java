package com.example.alex.partyapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.alex.partyapp.data.Constants.Status;

import java.util.List;

public interface ServersRepository {

    LiveData<Status> getStatus();

    MutableLiveData<Constants.Error> getError();

    LiveData<List<Server>> getServers();

    void login(String username, String password);

    void logout();

    void load();
}
