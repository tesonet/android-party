package com.svyd.tesonet.data.repository.servers.datasource;

import com.svyd.tesonet.data.repository.servers.ServersService;
import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.List;

import rx.Observable;

public class CloudServersDataSource implements ServersDataSource {

    private ServersService mService;

    public CloudServersDataSource(ServersService service) {
        mService = service;
    }

    @Override
    public Observable<List<Server>> getServers() {
        return mService.fetchServers();
    }
}
