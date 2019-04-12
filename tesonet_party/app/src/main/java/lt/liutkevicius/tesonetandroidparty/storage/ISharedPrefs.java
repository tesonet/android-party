package lt.liutkevicius.tesonetandroidparty.storage;

import lt.liutkevicius.tesonetandroidparty.network.model.Server;

import java.util.List;

public interface ISharedPrefs {
    void setToken(String token);

    String getToken();

    void setServers(String serversJson);

    List<Server> getServers();

    void clear();
}
