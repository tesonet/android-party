package lajevski.radoslav.tesonetparty.di;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServerAdapter;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServersMvpPresenter;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServersMvpView;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServersPresenter;
import lajevski.radoslav.tesonetparty.ui.fragments.login.LoginMvpPresenter;
import lajevski.radoslav.tesonetparty.ui.fragments.login.LoginMvpView;
import lajevski.radoslav.tesonetparty.ui.fragments.login.LoginPresenter;
import lajevski.radoslav.tesonetparty.utils.rx.AppSchedulerProvider;
import lajevski.radoslav.tesonetparty.utils.rx.SchedulerProvider;

/**
 * Created by Radoslav on 1/16/2018.
 */


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ServersMvpPresenter<ServersMvpView> provideLoadingPresenter(
            ServersPresenter<ServersMvpView> presenter) {
        return presenter;
    }

    @Provides
    ServerAdapter provideServerAdapter() {
        return new ServerAdapter(new ArrayList<Server>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
