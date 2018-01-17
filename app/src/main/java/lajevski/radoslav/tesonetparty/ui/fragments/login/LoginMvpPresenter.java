package lajevski.radoslav.tesonetparty.ui.fragments.login;


import lajevski.radoslav.tesonetparty.mvp.MvpPresenter;

/**
 * Created by Radoslav on 12/6/2017.
 */
public interface LoginMvpPresenter<V extends LoginMvpView>
        extends MvpPresenter<V> {

    void onViewPrepared();

    void onLoginButtonClick(String username, String password);
}

