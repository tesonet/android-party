package com.example.testio.presenter.impl;

import android.support.annotation.NonNull;
import com.example.testio.interactor.MainInteractor;
import com.example.testio.presenter.MainPresenter;
import com.example.testio.view.MainView;
import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
  /**
   * The interactor
   */
  @NonNull
  private final MainInteractor mInteractor;

  // The view is available using the mView variable

  @Inject
  public MainPresenterImpl(@NonNull MainInteractor interactor) {
    mInteractor = interactor;
  }

  @Override
  public void onStart(boolean viewCreated) {
    super.onStart(viewCreated);

    // Your code here. Your view is available using mView and will not be null until next onStop()
  }

  @Override
  public void onStop() {
    // Your code here, mView will be null after this method until next onStart()

    super.onStop();
  }

  @Override
  public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

    super.onPresenterDestroyed();
  }
}