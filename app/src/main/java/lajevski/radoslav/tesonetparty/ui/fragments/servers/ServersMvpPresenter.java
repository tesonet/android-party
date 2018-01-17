package lajevski.radoslav.tesonetparty.ui.fragments.servers;


import lajevski.radoslav.tesonetparty.mvp.MvpPresenter;

/**
 * Created by Radoslav on 12/6/2017.
 */
public interface ServersMvpPresenter<V extends ServersMvpView>
        extends MvpPresenter<V> {

    public void onViewPrepared();

    public void logOutClicked();

    public void loadingAnimationEnded();


}

