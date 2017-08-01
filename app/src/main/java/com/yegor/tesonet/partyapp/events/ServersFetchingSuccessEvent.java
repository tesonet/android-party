package com.yegor.tesonet.partyapp.events;

import com.yegor.tesonet.partyapp.model.Server;

import java.util.List;

/**
 * Event to notify that servers list loaded successfully
 */
public class ServersFetchingSuccessEvent {

    private List<Server> mServers;

    public ServersFetchingSuccessEvent(List<Server> servers) {
        mServers = servers;
    }

    public List<Server> getServers() {
        return mServers;
    }
}
