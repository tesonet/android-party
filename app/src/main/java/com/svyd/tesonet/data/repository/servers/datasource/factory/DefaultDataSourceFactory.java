package com.svyd.tesonet.data.repository.servers.datasource.factory;

import com.svyd.tesonet.data.networking.base.DataPersistenceStatus;
import com.svyd.tesonet.data.networking.framework.ServiceProvider;
import com.svyd.tesonet.data.repository.servers.ServersService;
import com.svyd.tesonet.data.repository.servers.datasource.CloudServersDataSource;
import com.svyd.tesonet.data.repository.servers.datasource.ServersDataSource;
import com.svyd.tesonet.data.repository.servers.datasource.PersistentServersDataSource;

public class DefaultDataSourceFactory implements ServersDataSourceFactory {

    private DataPersistenceStatus mDataStatus;

    public DefaultDataSourceFactory(DataPersistenceStatus status) {
        mDataStatus = status;
    }

    @Override
    public ServersDataSource provideDataSource() {
        if (mDataStatus.isStored()) {
            return new PersistentServersDataSource();
        } else {
            return new CloudServersDataSource(provideService());
        }
    }

    private ServersService provideService() {
        return new ServiceProvider().provideService(ServersService.class);
    }
}
