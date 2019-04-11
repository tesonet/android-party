package lt.liutkevicius.tesonetandroidparty.ui.login;

import android.util.Log;
import lt.liutkevicius.tesonetandroidparty.network.Repository;
import lt.liutkevicius.tesonetandroidparty.network.request.LoginRequest;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.ui.base.BasePresenter;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {
    private final Repository repository;
    private final SharedPrefs sharedPrefs;

    @Inject
    public LoginPresenter(Repository repository, SharedPrefs sharedPrefs) {
        this.repository = repository;
        this.sharedPrefs = sharedPrefs;
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
                    Log.d("LoginPresenter", "token = " + sharedPrefs.getToken());
                })
                .subscribe(token -> {
                }, throwable -> {
                    Log.d("err", throwable.getMessage());
                }));
    }
}
