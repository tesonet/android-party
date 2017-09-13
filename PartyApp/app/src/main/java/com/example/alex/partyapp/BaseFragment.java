package com.example.alex.partyapp;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;

public class BaseFragment<T extends ViewDataBinding> extends LifecycleFragment implements NavigationCallback {

    protected T binding;
    private NavigationCallback navigation;

    protected void showMessage(@StringRes int message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigation = (NavigationCallback) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigation = null;
    }

    @Override
    public void navigateToLogin() {
        if (navigation != null)
            navigation.navigateToLogin();
    }

    @Override
    public void navigateToServers() {
        if (navigation != null)
            navigation.navigateToServers();
    }

    @Override
    public void navigateToLoading() {
        if (navigation != null)
            navigation.navigateToLoading();
    }
}
