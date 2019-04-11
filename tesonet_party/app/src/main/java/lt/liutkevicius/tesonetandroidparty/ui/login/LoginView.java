package lt.liutkevicius.tesonetandroidparty.ui.login;

public interface LoginView {

    void showLoading();

    void hideLoading();

    void onError();

    void onLoggedIn();

    void showServers();
}
