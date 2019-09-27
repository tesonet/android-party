package com.example.tesonet.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tesonet.database.models.Server;
import com.example.tesonet.network.ApiInterface;
import com.example.tesonet.network.Service;
import com.example.tesonet.repositories.ServerRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerListViewModel extends AndroidViewModel {
    private ServerRepository serverRepository;
    private ApiInterface apiInterface;
    private String token;
    private MutableLiveData<List<Server>> serverList;
    private MutableLiveData<String> error;
    private Context context;

    public ServerListViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiInterface = Service.getRetrofitInstance().create(ApiInterface.class);
        serverRepository = new ServerRepository(application);
        error = new MutableLiveData<>();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void sendServerListRequest() {
        Call<List<Server>> serverResponse = apiInterface.getServer("Bearer " + token);
        serverResponse.enqueue(new Callback<List<Server>>() {
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                if (response.isSuccessful()) {
                    serverList.setValue(response.body());
                } else {
                    error.setValue("error");
                    Toast.makeText(context, "Token is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                error.setValue("error");
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteServers() {
        serverRepository.deleteServers();
    }

    public LiveData<String> getError(){
        return error;
    }

    public LiveData<List<Server>> getServerList() {
        if (serverList == null) {
            serverList = new MutableLiveData<>();
            sendServerListRequest();
        }
        return serverList;
    }
}
