package com.azzdorfrobotics.android.testio.features.servers;

import com.azzdorfrobotics.android.testio.data.ServerRepository;
import com.azzdorfrobotics.android.testio.network.TesonetApi;
import com.azzdorfrobotics.android.testio.network.model.response.Server;
import com.azzdorfrobotics.android.testio.preferences.AppPreferences;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * Created on 03.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class ServerCase {

    private final TesonetApi tesonetApi;
    private final AppPreferences appPreferences;
    private final ServerRepository serverRepository;

    @Inject public ServerCase(TesonetApi tesonetApi, AppPreferences appPreferences,
        ServerRepository serverRepository) {
        this.tesonetApi = tesonetApi;
        this.appPreferences = appPreferences;
        this.serverRepository = serverRepository;
    }

    public Observable<List<Server>> fetchServerList() {
        return tesonetApi.servers("Bearer " + appPreferences.getUserToken())
            .doOnNext(this::cacheServerList);
    }

    private void cacheServerList(List<Server> servers) {
        serverRepository.deleteAndPut(servers);
    }

    public Observable<List<Server>> getServers() {
        return serverRepository.queryAll().map(servers -> servers);
    }
}
