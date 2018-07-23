package com.njakawaii.tesonet.network;

import com.njakawaii.tesonet.App;
import com.njakawaii.tesonet.data.LoginModel;
import com.njakawaii.tesonet.data.ServerModel;
import com.njakawaii.tesonet.data.ServerModelData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelService {

    private RestApi restApi;

    public ModelService() {
        restApi = RestService.createRestService();
    }

    public Observable<LoginModel> login(String userName, String pass){
        return restApi.login(userName, pass)
                .retryWhen(new RetryWithDelay(3, 1000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<ServerModelData> getServers(){
        return restApi.getServers()
                .retryWhen(new RetryWithDelay(3, 1000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}

