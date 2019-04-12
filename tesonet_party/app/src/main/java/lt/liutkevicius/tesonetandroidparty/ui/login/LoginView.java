package lt.liutkevicius.tesonetandroidparty.ui.login;

public interface LoginView {

    void showLoading();

    void hideLoading();

    void onError(Exception e);

    void onLoggedIn();

    void showServers();
}
