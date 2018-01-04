package com.azzdorfrobotics.android.testio.features.servers;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.di.ServerComponent;
import com.azzdorfrobotics.android.testio.features.auth.AuthCase;
import com.azzdorfrobotics.android.testio.features.shared.BaseFragment;
import com.azzdorfrobotics.android.testio.features.shared.MainNavigator;
import com.azzdorfrobotics.android.testio.network.model.NetworkState;
import com.azzdorfrobotics.android.testio.network.model.request.LoginRequest;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class LoadingFragment extends BaseFragment {

    private static final String EXTRA_REFRESH_NAME = "EXTRA_REFRESH_NAME";
    private static final String EXTRA_REFRESH_PASSWORD = "EXTRA_REFRESH_PASSWORD";
    private static final int MIN_LOADING_SHOW = 1000;

    @BindView(R.id.ll_container) LinearLayout llContainer;

    @Inject ServerCase serverCase;
    @Inject AuthCase authCase;
    @Inject NetworkState networkState;

    private MainNavigator mainNavigator;

    public static LoadingFragment getInstance(String name, String password) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_REFRESH_NAME, name);
        bundle.putString(EXTRA_REFRESH_PASSWORD, password);
        LoadingFragment loadingFragment = new LoadingFragment();
        loadingFragment.setArguments(bundle);
        return loadingFragment;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainNavigator = (MainNavigator) getActivity();
        loadServerList(getArguments().getString(EXTRA_REFRESH_NAME, null),
            getArguments().getString(EXTRA_REFRESH_PASSWORD, null));
    }

    @Override protected int getLayoutId() {
        return R.layout.fragment_loading;
    }

    @Override protected void initializeInjector(Context context) {
        getComponent(ServerComponent.class, context).inject(LoadingFragment.this);
    }

    private void loadServerList(String name, String password) {
        if (networkState.checkConnect() == NetworkState.Connect.NONE) {
            handleWithSnackBar(llContainer, () -> loadServerList(name, password)).call(new Throwable("No internet connection"));
        }

        if (name != null && password != null) {
            addScreenAliveDisposable(Observable.combineLatest(
                Observable.interval(MIN_LOADING_SHOW, TimeUnit.MILLISECONDS).take(1),
                authCase.login(LoginRequest.getInstance(name, password)), Pair::new)
                .compose(schedulersAndFragmentAlive())
                .subscribe(__ -> {
                    addScreenAliveDisposable(serverCase.fetchServerList()
                        .compose(schedulersAndFragmentAlive())
                        .subscribe(_2 -> {
                            mainNavigator.showServerList();
                        }, throwable -> handleWithSnackBar(llContainer,
                            () -> loadServerList(name, password)).call(throwable)));
                }, throwable -> handleWithSnackBar(llContainer,
                    () -> loadServerList(name, password)).call(throwable)));
        } else {
            addScreenAliveDisposable(Observable.combineLatest(
                Observable.interval(MIN_LOADING_SHOW, TimeUnit.MILLISECONDS).take(1),
                serverCase.fetchServerList(), Pair::new)
                .compose(schedulersAndFragmentAlive())
                .subscribe(__ -> {
                    mainNavigator.showServerList();
                }, throwable -> handleWithSnackBar(llContainer,
                    () -> loadServerList(name, password)).call(throwable)));
        }
    }
}
