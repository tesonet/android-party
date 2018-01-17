package lajevski.radoslav.tesonetparty.mvp;

/**
 * Created by Radoslav on 1/16/2018.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void setUserAsLoggedOut();

    void handleApiError(Throwable t);
}