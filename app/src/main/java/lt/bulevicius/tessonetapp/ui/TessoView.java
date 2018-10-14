package lt.bulevicius.tessonetapp.ui;

/**
 * The interface Tesso view.
 */
public interface TessoView {
    /**
     * Show error.
     *
     * @param error the error
     */
    void onError(Throwable error);

    /**
     * Start progress.
     */
    void showProgress();

    /**
     * Hide progress.
     */
    void hideProgress();
}
