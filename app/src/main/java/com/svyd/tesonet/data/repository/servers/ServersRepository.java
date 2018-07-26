package com.svyd.tesonet.data.repository.servers;

import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.List;

import rx.Observable;

public interface ServersRepository {
    Observable<List<Server>> getServers();
}
