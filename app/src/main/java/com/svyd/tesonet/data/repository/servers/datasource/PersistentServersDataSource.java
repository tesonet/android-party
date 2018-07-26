package com.svyd.tesonet.data.repository.servers.datasource;

import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class PersistentServersDataSource implements ServersDataSource {


    public PersistentServersDataSource() {

    }

    @Override
    public Observable<List<Server>> getServers() {
        return Observable.create(subscriber -> subscriber.onNext(new ArrayList<>()));
    }
}
