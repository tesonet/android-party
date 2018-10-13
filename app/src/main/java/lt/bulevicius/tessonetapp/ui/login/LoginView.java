package lt.bulevicius.tessonetapp.ui.login;

/**
 * The interface Login view.
 */
interface LoginView {

    /**
     * Show error.
     *
     * @param error the error
     */
    void showError(Throwable error);

    /**
     * Start progress.
     */
    void startProgress();

    /**
     * Hide progress.
     */
    void hideProgress();

    /**
     * Login success.
     */
    void loginSuccess();
}
