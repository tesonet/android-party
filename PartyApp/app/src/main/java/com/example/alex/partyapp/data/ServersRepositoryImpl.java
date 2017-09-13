package com.example.alex.partyapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.alex.partyapp.utils.SingleLiveEvent;
import com.example.alex.partyapp.network.TesonetApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.alex.partyapp.data.Constants.Status;

import java.net.HttpURLConnection;
import java.util.List;

public class ServersRepositoryImpl implements ServersRepository {

    private static final String TOKEN_BEGIN = "Bearer ";
    private final TesonetApi api;
    private final TokenStorage tokenStorage;
    private final ServersStorage serversStorage;

    private final MutableLiveData<Status> status = new MutableLiveData<>();
    private final MutableLiveData<Constants.Error> error = new SingleLiveEvent<>();
    private final MutableLiveData<List<Server>> servers = new MutableLiveData<>();

    @Override
    public LiveData<Status> getStatus() {
        return status;
    }

    @Override
    public MutableLiveData<Constants.Error> getError() {
        return error;
    }

    @Override
    public LiveData<List<Server>> getServers() {
        return servers;
    }

    public ServersRepositoryImpl(TesonetApi api, TokenStorage tokenStorage, ServersStorage serversStorage) {
        this.api = api;
        this.tokenStorage = tokenStorage;
        this.serversStorage = serversStorage;
        if (tryOffline())
            post(Status.LOADED, Constants.Error.OFFLINE_RESULTS);
        else
            post(Status.LOGGED_OUT, Constants.Error.NONE);
    }

    private void post(Status status, Constants.Error error) {
        this.status.setValue(status);
        this.error.setValue(error);
    }

    public boolean tryOffline() {
        String token = tokenStorage.load();
        List<Server> serversData = serversStorage.load();
        if (token != null && serversData != null) {
            servers.postValue(serversData);
            return true;
        } else if (token == null) {
            logout();
            return false;
        }
        return false;
    }

    @Override
    public void login(String username, String password) {
        post(Status.LOGGING, Constants.Error.NONE);
        api.login(new Account(username, password)).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token body = response.body();
                if (body != null) {
                    tokenStorage.store(body.getToken());
                    post(Status.LOGGED_IN, Constants.Error.NONE);
                } else {
                    post(Status.LOGGED_OUT, Constants.Error.AUTHORIZATION_FAILED);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                post(Status.LOGGED_OUT, Constants.Error.NETWORK_ERROR);
            }
        });
    }

    @Override
    public void logout() {
        tokenStorage.store(null);
        post(Status.LOGGED_OUT, Constants.Error.NONE);
    }

    @Override
    public void load() {
        String token = tokenStorage.load();
        if (token == null) {
            logout();
            return;
        }

        post(Status.LOADING, Constants.Error.NONE);
        api.getServers(TOKEN_BEGIN + token).enqueue(new Callback<List<Server>>() {
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                List<Server> body = response.body();
                if (body != null) {
                    serversStorage.store(body);
                    servers.postValue(body);
                    post(Status.LOADED, Constants.Error.NONE);
                } else
                    post(Status.LOGGED_OUT, response.code() == HttpURLConnection.HTTP_UNAUTHORIZED ?
                            Constants.Error.AUTHORIZATION_FAILED : Constants.Error.FAILED);
            }

            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                post(tryOffline() ? Status.LOADED : Status.LOGGED_IN, Constants.Error.NETWORK_ERROR);
            }
        });
    }
}
