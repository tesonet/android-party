package lt.bulevicius.tessonetapp.ui.login;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import lt.bulevicius.tessonetapp.network.entities.auth.TokenRequest;
import lt.bulevicius.tessonetapp.network.models.AuthModel;
import lt.bulevicius.tessonetapp.network.models.CountryModel;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import lt.bulevicius.tessonetapp.ui.BasePresenter;
import lt.bulevicius.tessonetapp.ui.error.ErrorHandlerImpl;
import timber.log.Timber;

/**
 * The type Login presenter.
 */
public final class LoginPresenter extends BasePresenter<LoginView> {

    private final AuthModel authModel;
    private final LocalDataProvider localDataProvider;
    private final CountryModel countryModel;
    private final ErrorHandlerImpl errorHandler;

    /**
     * Instantiates a new Login presenter.
     *
     * @param authModel         the auth model
     * @param localDataProvider the local data provider
     */
    @Inject
    LoginPresenter(AuthModel authModel, CountryModel countryModel, LocalDataProvider localDataProvider, ErrorHandlerImpl errorHandler) {
        this.authModel = authModel;
        this.localDataProvider = localDataProvider;
        this.countryModel = countryModel;
        this.errorHandler = errorHandler;
    }


    /**
     * Logs in saves user token.
     * So im doing the country call because of loader stuff... and ignoring countries because of lack of
     * local storage implementation. Should save the the countries to database or whatever and then
     * proceed to next window (countryListView) however since im limited on time resources leaving it here.
     * @param username the username
     * @param password the password
     */
    final void doLogin(@Nullable String username, @Nullable String password) {
        getView().showProgress();
        subscriptions.add(authModel.doLogin(new TokenRequest(username, password))
                                   .doOnNext(tokenResponse -> Timber.d("token: %s", tokenResponse.getToken()))
                                   .doOnNext(tokenResponse -> getView().loginSuccess())
                                   .flatMap(tokenResponse -> {
                                       localDataProvider.setToken(tokenResponse.getToken());
                                       return countryModel.getCountryList();
                                   })
                                   .subscribe(
                                           countries -> {
                                               localDataProvider.setCountries(countries);
                                               getView().hideProgress();
                                               getView().onDataSuccess();
                                           },
                                           e -> {
                                               getView().hideProgress();
                                               getView().onError(errorHandler.handleError(e));
                                           }));
    }
}
