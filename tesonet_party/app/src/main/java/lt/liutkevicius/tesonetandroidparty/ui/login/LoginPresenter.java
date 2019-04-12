package lt.liutkevicius.tesonetandroidparty.ui.login;

import lt.liutkevicius.tesonetandroidparty.network.Repository;
import lt.liutkevicius.tesonetandroidparty.network.error.ErrorHandler;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.ui.base.BasePresenter;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {
    private final Repository repository;
    private final SharedPrefs sharedPrefs;
    private final ErrorHandler errorHandler;

    @Inject
    public LoginPresenter(Repository repository, SharedPrefs sharedPrefs, ErrorHandler errorHandler) {
        this.repository = repository;
        this.sharedPrefs = sharedPrefs;
        this.errorHandler = errorHandler;
    }

    public void login(String username, String pass) {
        if (!hasView()) {
            return;
        }
        subscriptions.add(repository.executeLogin(new LoginRequest(username, pass))
                .doOnSubscribe(disposable -> getView().showLoading())
                .doOnNext(tokenResponse -> {
                    getView().onLoggedIn();
                    sharedPrefs.setToken(tokenResponse.getToken());
                })
                .flatMap(token -> repository.getServers())
                .doOnNext(jsonElement -> {
                    sharedPrefs.setServers(jsonElement.toString());
                    getView().showServers();
                })
                .subscribe(token -> {
                }, throwable -> {
                    getView().hideLoading();
                    getView().onError(new Exception(errorHandler.getErrorMessage(throwable)));
                }));
    }
}
