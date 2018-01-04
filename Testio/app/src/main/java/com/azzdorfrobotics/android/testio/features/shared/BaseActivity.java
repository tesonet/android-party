package com.azzdorfrobotics.android.testio.features.shared;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final CompositeDisposable uiCompositeDisposable
        = new CompositeDisposable(); // -> pause
    private final CompositeDisposable screenAliveCompositeDisposable
        = new CompositeDisposable(); // -> destroy

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initializeInjector();

        ButterKnife.bind(this);
    }

    protected abstract void initializeInjector();

    protected abstract int getLayoutId();

    /**
     * Add subscription that have to be alive between onResume -> onPause
     */
    protected void addUiDisposable(Disposable disposable) {
        uiCompositeDisposable.add(disposable);
    }

    protected void addUiDisposables(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            addUiDisposable(disposable);
        }
    }

    protected void addScreenAliveDisposables(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            addScreenAliveDisposable(disposable);
        }
    }

    /**
     * Add subscription that have to be alive between onCreate -> onDestroy
     */
    protected void addScreenAliveDisposable(Disposable Disposable) {
        screenAliveCompositeDisposable.add(Disposable);
    }

    protected <T> ObservableTransformer<T, T> schedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override protected void onPause() {
        super.onPause();
        uiCompositeDisposable.clear();
    }

    protected int getScreenName() {
        return 0;
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        screenAliveCompositeDisposable.clear();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(@StringRes int resMsgId) {
        Toast.makeText(this, resMsgId, Toast.LENGTH_SHORT).show();
    }
}
