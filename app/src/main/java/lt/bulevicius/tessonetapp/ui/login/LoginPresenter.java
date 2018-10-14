package lt.bulevicius.tessonetapp.ui.login;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import lt.bulevicius.tessonetapp.network.entities.auth.TokenRequest;
import lt.bulevicius.tessonetapp.network.models.AuthModel;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import lt.bulevicius.tessonetapp.ui.BasePresenter;

/**
 * The type Login presenter.
 */
public final class LoginPresenter extends BasePresenter<LoginView> {

    private final AuthModel authModel;
    private final LocalDataProvider localDataProvider;

    /**
     * Instantiates a new Login presenter.
     *
     * @param authModel         the auth model
     * @param localDataProvider the local data provider
     */
    @Inject
    LoginPresenter(AuthModel authModel, LocalDataProvider localDataProvider) {
        this.authModel = authModel;
        this.localDataProvider = localDataProvider;
    }


    /**
     * Log's in saves user token.
     *
     * @param username the username
     * @param password the password
     */
    final void doLogin(@Nullable String username, @Nullable String password) {
        subscriptions.add(authModel.doLogin(new TokenRequest(username, password))
                                   .doOnSubscribe(d -> getView().showProgress())
                                   .subscribe(
                                           tokenResponse -> {
                                               localDataProvider.setToken(tokenResponse.getToken());
                                               getView().hideProgress();
                                               getView().loginSuccess();
                                           },
                                           e -> {
                                               getView().hideProgress();
                                               getView().onError(e);
                                           }));
    }
}
