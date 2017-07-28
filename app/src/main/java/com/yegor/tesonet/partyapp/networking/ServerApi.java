package com.yegor.tesonet.partyapp.networking;

import com.yegor.tesonet.partyapp.events.LoadStartEvent;
import com.yegor.tesonet.partyapp.events.LoginSuccessfulEvent;
import com.yegor.tesonet.partyapp.events.NetworkErrorEvent;
import com.yegor.tesonet.partyapp.events.ServersFetchingSuccessEvent;
import com.yegor.tesonet.partyapp.framework.DataProvider;
import com.yegor.tesonet.partyapp.model.Account;
import com.yegor.tesonet.partyapp.model.Server;
import com.yegor.tesonet.partyapp.model.TokenResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to do network
 */
public class ServerApi {

    private TesonetService mService;

    public ServerApi() {
        mService = ServiceProvider.createService(TesonetService.class);
    }

    public void login(Account account) {
        Call<TokenResponse> login = mService.login(account);
        login.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.errorBody() != null) {
                    EventBus.getDefault().post(new NetworkErrorEvent("Login Failed"));
                } else {
                    DataProvider.storeToken(response.body().getToken());
                    EventBus.getDefault().post(new LoginSuccessfulEvent());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                EventBus.getDefault().post(new NetworkErrorEvent(t.getMessage()));
            }
        });
    }

    /**
     * checks servers list in storage and is it "fresh" enough, otherwise loads from network
     */
    public void loadServers() {
        long lastUpdate = DataProvider.getLastUpdate();
        if (lastUpdate == DataProvider.DEFAULT_VALUE || !isToday(lastUpdate)) {
            loadExternal();
        } else {
            DataProvider.loadInternal();
        }
    }

    /**
     * checks is given time is the same day as now
     *
     * @param time given time
     * @return is given time is the same day as now
     */
    public boolean isToday(long time) {
        return TimeUnit.MILLISECONDS.toDays(time) == TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis());
    }

    /**
     * loads servers list from network, and sends corresponding events
     */
    public void loadExternal() {
        EventBus.getDefault().post(new LoadStartEvent());
        Call<List<Server>> servers = mService.fetchList();
        servers.enqueue(new Callback<List<Server>>() {
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                if (response.errorBody() != null) {
                    EventBus.getDefault().post(new NetworkErrorEvent("Fetching Failed"));
                } else {
                    DataProvider.storeList(response.body());
                    DataProvider.setLastUpdate(System.currentTimeMillis());
                    EventBus.getDefault().post(new ServersFetchingSuccessEvent(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                EventBus.getDefault().post(new NetworkErrorEvent(t.getMessage()));
            }
        });
    }
}
