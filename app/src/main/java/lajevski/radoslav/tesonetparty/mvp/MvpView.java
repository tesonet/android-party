package lajevski.radoslav.tesonetparty.mvp;

import android.support.annotation.StringRes;

/**
 * Created by Radoslav on 1/16/2018.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void hideKeyboard();

    void showToast(String text);

    void vibrate();


}