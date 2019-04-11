package lt.liutkevicius.tesonetandroidparty.ui.login;

public interface LoginView {

    void showLoading();

    void onError();

    void onLoggedIn();

    void showServers();
}
