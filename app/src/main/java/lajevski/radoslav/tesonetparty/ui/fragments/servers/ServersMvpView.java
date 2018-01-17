package lajevski.radoslav.tesonetparty.ui.fragments.servers;

import java.util.List;

import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.mvp.MvpView;

/**
 * Created by Radoslav on 12/6/2017.
 */

public interface ServersMvpView extends MvpView {

    void hideToolbar();

    void showToolbar();

    void showLoading();

    void hideLoading();

    void updateServersList(List<Server> serverList);

    void openLoginActivity();
}
