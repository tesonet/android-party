package com.svyd.tesonet.presentation.base;

/**
 * Base interface for all presenters.
 */

public interface BaseView {
    void showProgress();
    void hideProgress();
    void showMessage(String message);
}
