package com.svyd.tesonet.data.repository.servers.datasource;

import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.List;

import rx.Observable;

public interface ServersDataSource {
    Observable<List<Server>> getServers();
}
