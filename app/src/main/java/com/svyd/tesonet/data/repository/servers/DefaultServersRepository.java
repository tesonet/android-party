package com.svyd.tesonet.data.repository.servers;

import com.svyd.tesonet.data.repository.servers.datasource.factory.ServersDataSourceFactory;
import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.List;

import rx.Observable;

public class DefaultServersRepository implements ServersRepository {

    private ServersDataSourceFactory mDataSourceFactory;

    public DefaultServersRepository(ServersDataSourceFactory factory) {
        mDataSourceFactory = factory;
    }

    @Override
    public Observable<List<Server>> getServers() {
        return mDataSourceFactory.provideDataSource().getServers();
    }
}
