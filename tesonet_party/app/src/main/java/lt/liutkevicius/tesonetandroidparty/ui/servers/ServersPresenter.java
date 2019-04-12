package lt.liutkevicius.tesonetandroidparty.ui.servers;

import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.ui.base.BasePresenter;

import javax.inject.Inject;

public class ServersPresenter extends BasePresenter<ServersView> {

    @Inject
    SharedPrefs sharedPrefs;

    @Inject
    public ServersPresenter() {
    }

    void logout() {
        sharedPrefs.clear();
        getView().showLogin();
    }
}
