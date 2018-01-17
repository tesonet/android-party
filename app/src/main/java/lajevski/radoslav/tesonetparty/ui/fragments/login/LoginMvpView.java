package lajevski.radoslav.tesonetparty.ui.fragments.login;


import lajevski.radoslav.tesonetparty.mvp.MvpView;

/**
 * Created by Radoslav on 12/6/2017.
 */

public interface LoginMvpView extends MvpView {

    void openMainActivity();

    void setLoginButtonStatus(boolean status);

}
