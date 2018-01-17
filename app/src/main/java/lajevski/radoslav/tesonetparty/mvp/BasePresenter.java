package lajevski.radoslav.tesonetparty.mvp;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.utils.rx.SchedulerProvider;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;

    private final SchedulerProvider mSchedulerProvider;

    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    @Override
    public void setUserAsLoggedOut() {
    }

    @Override
    public void handleApiError(Throwable t) {

        if(t.getMessage().equals("Unauthorized")){

            getMvpView().showMessage("Invalid Credentials");

        } else if ( t.getMessage().equals("connection_error")){

            getMvpView().showMessage("No internet connection!");

            getMvpView().vibrate();

        } else {

            getMvpView().showMessage("Error");

        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}