package com.azzdorfrobotics.android.testio.features.shared;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.di.HasComponent;
import com.azzdorfrobotics.android.testio.features.shared.utils.SnackBarUtil;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public abstract class BaseFragment extends Fragment {

    private final CompositeDisposable uiCompositeDisposable = new CompositeDisposable(); // -> pause
    private final CompositeDisposable screenVisibleDisposable = new CompositeDisposable();
    // -> stop
    private final CompositeDisposable screenAliveCompositeDisposable = new CompositeDisposable();
    // -> destroy

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void initializeInjector(Context context);

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType,
        Context context) {
        return componentType.cast(((HasComponent<C>) context).getComponent());
    }

    private Unbinder unbinder;
    private Snackbar snackbar;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initializeInjector(getActivity());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override public void onPause() {
        super.onPause();
        uiCompositeDisposable.clear();
    }

    @Override public void onStop() {
        super.onStop();
        screenVisibleDisposable.clear();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        screenAliveCompositeDisposable.clear();
        if (unbinder != null) unbinder.unbind();
        if (snackbar != null && snackbar.isShownOrQueued()) {
            snackbar.dismiss();
        }
    }

    protected void addScreenAliveDisposables(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            addScreenAliveDisposable(disposable);
        }
    }

    /**
     * Subscribe/unsubscribe on create/destroy until screen is in memory,
     * use for independent of ui operations
     */
    protected void addScreenAliveDisposable(Disposable disposable) {
        screenAliveCompositeDisposable.add(disposable);
    }

    /**
     * Subscribe/unsubscribe on resume/pause when screen in foreground and user can interact with it
     */
    protected void addUiDisposable(Disposable disposable) {
        uiCompositeDisposable.add(disposable);
    }

    /**
     * Subscribe/unsubscribe on start/stop when screen visible
     */
    protected void addScreenVisibleDisposable(Disposable disposable) {
        screenVisibleDisposable.add(disposable);
    }

    /**
     * Subscribe/unsubscribe on start/stop when screen visible
     */
    protected void addScreenVisibleDisposables(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            addScreenVisibleDisposable(disposable);
        }
    }

    protected void addUiDisposables(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            addUiDisposable(disposable);
        }
    }

    protected <T> ObservableTransformer<T, T> fragmentAlive() {
        return observable -> observable.filter(t -> isFragmentAlive());
    }

    protected <T> ObservableTransformer<T, T> schedulersAndFragmentAlive() {
        return observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose((ObservableTransformer<T, T>) tObservable -> tObservable.filter(
                t -> isFragmentAlive()));
    }

    protected <T> ObservableTransformer<T, T> schedulersAndFragmentAliveAndRefresh(
        SwipeRefreshLayout swipeContainer) {
        return observable -> observable.compose(schedulersAndFragmentAlive())
            .doOnDispose(() -> swipeContainer.setRefreshing(true))
            .doOnTerminate(() -> swipeContainer.setRefreshing(false));
    }

    protected <T> ObservableTransformer<T, T> fragmentAliveAndRefresh(
        SwipeRefreshLayout swipeContainer) {
        return observable -> observable.compose(fragmentAlive())
            .doOnDispose(() -> swipeContainer.setRefreshing(true))
            .doOnTerminate(() -> swipeContainer.setRefreshing(false));
    }

    protected Action1<Throwable> handleWithSnackBar(View view, @NonNull Action0 retryAction) {
        return e -> {
            snackbar = Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.all_retry, v -> retryAction.call());
            SnackBarUtil.styleSnackBar(snackbar, getContext().getApplicationContext());
            snackbar.show();
        };
    }

    protected boolean isFragmentAlive() {
        return getActivity() != null
            && isAdded()
            && !isDetached()
            && getView() != null
            && !isRemoving();
    }
}
