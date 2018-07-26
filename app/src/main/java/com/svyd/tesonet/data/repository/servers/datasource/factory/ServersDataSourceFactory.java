package com.svyd.tesonet.data.repository.servers.datasource.factory;

import com.svyd.tesonet.data.repository.servers.datasource.ServersDataSource;

public interface ServersDataSourceFactory {
    ServersDataSource provideDataSource();
}
