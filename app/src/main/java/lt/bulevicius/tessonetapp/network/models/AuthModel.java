package lt.bulevicius.tessonetapp.network.models;

import javax.inject.Inject;

import io.reactivex.Observable;
import lt.bulevicius.tessonetapp.app.utils.SchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenRequest;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenResponse;

/**
 * The type Auth model.
 */
public final class AuthModel {

    private final TessonetApi api;
    private final SchedulerProvider schedulerProvider;

    /**
     * Instantiates a new Auth model.
     *
     * @param api               the api
     * @param schedulerProvider the scheduler provider
     */
    @Inject
    public AuthModel(TessonetApi api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    /**
     * Do login observable.
     *
     * @param tokenRequest the token request
     * @return the observable
     */
    public final Observable<TokenResponse> doLogin(TokenRequest tokenRequest) {
        return api.login(tokenRequest).compose(schedulerProvider.applySchedulers());
    }

}
