package com.svyd.tesonet.presentation.base;

import android.os.Bundle;

/**
 * Base class for all presenter providers
 *
 * @param <P> Presenter type which will be created
 * @param <V> View type which is needed for presenter as a reference to access
 */

public interface PresenterProvider<P extends BasePresenter, V extends BaseView> {
    P providePresenter(Bundle args, V view);
}
