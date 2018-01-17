package lajevski.radoslav.tesonetparty.ui.base;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.BindView;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.di.ActivityComponent;
import lajevski.radoslav.tesonetparty.mvp.MvpView;
import lajevski.radoslav.tesonetparty.ui.activities.LoginActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Radoslav on 1/16/2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private View mCoordinatorLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Convenience method for switching content, calling activity's method.
     *
     * @param fragment the fragment
     * @param fragmentTag tag, that identifies fragment in back stack trace
     */
    public void switchContent(Fragment fragment, String fragmentTag) {
        ((BaseActivity) getActivity()).switchContent(fragment, fragmentTag);
    }

    /**
     * Initialize and obtain references to views
     */
    private void initViews() {

        mCoordinatorLayout = getActivity().findViewById(R.id.coordinator_layout);
    }

    public ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {
        Intent intent = LoginActivity.getStartIntent(getActivity());
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(getCoordinatorLayout(), message, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }

    @Override
    public void showMessage(int resId) {
        Snackbar snackbar = Snackbar
                .make(getCoordinatorLayout(), getString(resId), Snackbar.LENGTH_SHORT);

        snackbar.show();
    }

    @Override
    public void hideKeyboard() {
        if(getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void vibrate() {

    }

    public View getCoordinatorLayout() {
        return mCoordinatorLayout;
    }

    public Toolbar getToolbar() {
        return ((BaseActivity) getActivity()).requestActionBar();
    }
}
