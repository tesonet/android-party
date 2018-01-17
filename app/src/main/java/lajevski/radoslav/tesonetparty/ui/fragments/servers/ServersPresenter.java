package lajevski.radoslav.tesonetparty.ui.fragments.servers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.mvp.BasePresenter;
import lajevski.radoslav.tesonetparty.utils.rx.SchedulerProvider;


/**
 * Created by Radoslav on 12/6/2017.
 */

public class ServersPresenter<V extends ServersMvpView> extends BasePresenter<V>
        implements ServersMvpPresenter<V> {

    @Inject
    public ServersPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getMvpView().hideToolbar();

        getCompositeDisposable().add(getDataManager()
                .getServers(getDataManager().getToken())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Server>>() {
                    @Override
                    public void accept(List<Server> servers) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().updateServersList(servers);

                        getMvpView().hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })


        );
    }

    @Override
    public void loadingAnimationEnded() {
        getMvpView().showToolbar();
    }

    @Override
    public void logOutClicked() {
        getDataManager().setToken(null);
        getMvpView().openLoginActivity();
    }
}
