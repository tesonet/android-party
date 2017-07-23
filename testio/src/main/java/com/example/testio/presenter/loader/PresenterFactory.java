package com.example.testio.presenter.loader;

import android.support.annotation.NonNull;
import com.example.testio.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
  @NonNull
  T create();
}
