package com.njakawaii.tesonet.login;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.text.TextUtils;

import com.njakawaii.tesonet.App;
import com.njakawaii.tesonet.main.MainActivity;
import com.njakawaii.tesonet.R;
import com.njakawaii.tesonet.data.LoginModel;
import com.njakawaii.tesonet.data.ServerModelData;
import com.njakawaii.tesonet.data.ServersDB;
import com.orhanobut.hawk.Hawk;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.njakawaii.tesonet.Constants.TOKEN;


public class LoginPresenter implements LoginContract.Actions, LifecycleObserver {
    private BaseActivity activity;
    private CompositeDisposable subscriptions;
    private LoginContract.View view;
    private final int MIN_LENGTH = 4;
    private ServersDB mDb;

    public LoginPresenter(BaseActivity activity, LoginContract.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void doLogin() {
        if (activity.isNetworkAvailable(activity)) {
            view.showProgress(true);
            if (isValidData()) {
                doLoginCall();
            }
        } else activity.checkConnection();
    }

    private void doLoginCall() {
        App.getInstance().getModelService().login(view.getUserName(), view.getPassword()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).delay(500, TimeUnit.MILLISECONDS).subscribe(new Observer<LoginModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(LoginModel value) {
                view.showProgress(false);
                if (value != null && !value.getToken().isEmpty()) {
                    Hawk.put(TOKEN, value.getToken());
                    getServers();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getServers() {
        if (activity.isNetworkAvailable(activity)) {
            view.showFetchingServers(true);
            getServersCall();
        } else {
            activity.checkConnection();
        }
    }

    private void getServersCall() {
        App.getInstance().getModelService().getServers().delay(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ServerModelData>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(ServerModelData value) {
                view.showFetchingServers(false);
                saveDataToDB(value);
                openMainActivity();
            }

            @Override
            public void onError(Throwable e) {

                view.showFetchingServers(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void saveDataToDB(ServerModelData value) {
        mDb = App.getInstance().getDatabase();
        mDb.serverDao().insertAll(value.getData());
    }

    private void openMainActivity() {
        activity.runOnUiThread(() -> {
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        });

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(){
        initActions();
        if (!Hawk.get(TOKEN, "").isEmpty()) getServers();
    }

    private void initActions() {
        if (subscriptions == null){
            subscriptions = new CompositeDisposable();
        }
        subscriptions.add(view.loginAction().subscribe(o -> doLogin()));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(){
        if (subscriptions!=null && !subscriptions.isDisposed()){
            subscriptions.dispose();
            subscriptions.clear();
            subscriptions = null;
        }
    }

    private boolean isValidData() {
         if (TextUtils.isEmpty(view.getUserName()) ||
                 view.getUserName().length() < MIN_LENGTH){
             view.showUserNameError(activity.getString(R.string.error_user_name));
             return false;
         }
        if (TextUtils.isEmpty(view.getPassword()) ||
                view.getPassword().length() < MIN_LENGTH){
            view.showPassError(activity.getString(R.string.error_invalid_password));
            return false;
        }
        return true;
    }

}
