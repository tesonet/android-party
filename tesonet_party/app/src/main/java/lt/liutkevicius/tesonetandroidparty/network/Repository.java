package lt.liutkevicius.tesonetandroidparty.network;


import io.reactivex.Observable;
import lt.liutkevicius.tesonetandroidparty.network.model.Token;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import lt.liutkevicius.tesonetandroidparty.network.schedulers.SchedulerProvider;

import javax.inject.Inject;

public class Repository {
    private PartyApi partyApi;
    private SchedulerProvider schedulerProvider;

    @Inject
    public Repository(PartyApi partyApi, SchedulerProvider schedulerProvider) {
        this.partyApi = partyApi;
        this.schedulerProvider = schedulerProvider;
    }

    /*
     * method to call login api
     * */
    public final Observable<Token> executeLogin(LoginRequest loginRequest) {
        return partyApi.login(loginRequest).compose(schedulerProvider.applySchedulers());
    }
}
