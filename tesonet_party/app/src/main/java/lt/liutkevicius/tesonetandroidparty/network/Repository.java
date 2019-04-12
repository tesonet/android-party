package lt.liutkevicius.tesonetandroidparty.network;


import com.google.gson.JsonElement;
import io.reactivex.Observable;
import lt.liutkevicius.tesonetandroidparty.network.model.Token;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import lt.liutkevicius.tesonetandroidparty.network.schedulers.SchedulerProvider;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.utils.Constants;

import javax.inject.Inject;

public class Repository {
    private final PartyApi partyApi;
    private final SchedulerProvider schedulerProvider;
    private final SharedPrefs sharedPrefs;

    @Inject
    public Repository(PartyApi partyApi, SchedulerProvider schedulerProvider, SharedPrefs sharedPrefs) {
        this.partyApi = partyApi;
        this.schedulerProvider = schedulerProvider;
        this.sharedPrefs = sharedPrefs;
    }

    public final Observable<Token> executeLogin(LoginRequest loginRequest) {
        return partyApi.login(loginRequest).compose(schedulerProvider.applySchedulers());
    }

    public final Observable<JsonElement> getServers() {
        return partyApi.getServers(String.format(Constants.BEARER + " %s", sharedPrefs.getToken()))
                .compose(schedulerProvider.applySchedulers());
    }
}
