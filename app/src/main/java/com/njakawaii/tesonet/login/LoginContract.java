package com.njakawaii.tesonet.login;

import io.reactivex.Observable;

public class LoginContract {
    interface View{
        String getUserName();
        String getPassword();
        Observable<Object> loginAction();
        void showPassError(String error);
        void showUserNameError(String error);
        void showProgress(boolean isShow);
        void showFetchingServers(boolean isShow);
    }

    interface Actions{
        void doLogin();
        void getServers();
    }
}
