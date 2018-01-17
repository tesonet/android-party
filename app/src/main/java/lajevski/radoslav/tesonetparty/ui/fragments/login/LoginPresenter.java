package lajevski.radoslav.tesonetparty.ui.fragments.login;


import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.data.network.model.LoginResponse;
import lajevski.radoslav.tesonetparty.data.network.model.User;
import lajevski.radoslav.tesonetparty.mvp.BasePresenter;
import lajevski.radoslav.tesonetparty.utils.AppString;
import lajevski.radoslav.tesonetparty.utils.rx.SchedulerProvider;

/**
 * Created by Radoslav on 12/6/2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        if(!AppString.isEmpty(getDataManager().getToken())){
            getMvpView().openMainActivity();
        }
    }

    @Override
    public void onLoginButtonClick(final String username, String password) {

        if (username == null || username.isEmpty()) {
            getMvpView().showMessage(R.string.empty_username);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().showMessage(R.string.empty_password);
            return;
        }

        getMvpView().setLoginButtonStatus(false);

        getCompositeDisposable().add(getDataManager()
                .getToken(new User(username, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse response) throws Exception {

                        getDataManager().setToken(response.getToken());

                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideKeyboard();
                        getMvpView().openMainActivity();
                        getMvpView().setLoginButtonStatus(true);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        handleApiError(throwable);

                        getMvpView().setLoginButtonStatus(true);
                    }
                }));
    }
}
