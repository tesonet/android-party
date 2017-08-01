package com.yegor.tesonet.partyapp.networking;

import com.yegor.tesonet.partyapp.events.LoadStartEvent;
import com.yegor.tesonet.partyapp.events.LoginSuccessfulEvent;
import com.yegor.tesonet.partyapp.events.NetworkErrorEvent;
import com.yegor.tesonet.partyapp.events.ServersFetchingSuccessEvent;
import com.yegor.tesonet.partyapp.framework.DataProvider;
import com.yegor.tesonet.partyapp.model.Account;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class to do network
 */
public class ServerApi {

    private TesonetService mService;

    public ServerApi() {
        mService = ServiceProvider.createService(TesonetService.class);
    }

    public void login(Account account) {
        mService.login(account)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tokenResponse -> {
                            DataProvider.storeToken(tokenResponse.getToken());
                            EventBus.getDefault().post(new LoginSuccessfulEvent());
                        },
                        throwable -> EventBus.getDefault().post(new NetworkErrorEvent(throwable.getMessage())));
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
        mService.fetchList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            DataProvider.storeList(list);
                            DataProvider.setLastUpdate(System.currentTimeMillis());
                            EventBus.getDefault().post(new ServersFetchingSuccessEvent(list));
                        },
                        throwable -> EventBus.getDefault().post(new NetworkErrorEvent(throwable.getMessage())));
    }
}
