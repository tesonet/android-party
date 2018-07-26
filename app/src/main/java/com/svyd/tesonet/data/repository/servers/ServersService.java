package com.svyd.tesonet.data.repository.servers;

import com.svyd.tesonet.data.repository.servers.model.Server;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ServersService {

    @GET("servers")
    Observable<List<Server>> fetchServers();

}