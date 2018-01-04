package com.azzdorfrobotics.android.testio.features.shared;

/**
 * Created on 03.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public interface MainNavigator {

    void showLogin();

    void showLoading(String name, String password);

    void showServerList();
}
