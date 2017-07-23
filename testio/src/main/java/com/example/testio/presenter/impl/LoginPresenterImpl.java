package com.example.testio.presenter.impl;

import android.support.annotation.NonNull;
import com.example.testio.interactor.LoginInteractor;
import com.example.testio.models.User;
import com.example.testio.presenter.LoginPresenter;
import com.example.testio.view.LoginView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView>
    implements LoginPresenter {
  /**
   * The interactor
   */
  @NonNull
  private final LoginInteractor mInteractor;

  // The view is available using the mView variable

  @Inject
  public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
    mInteractor = interactor;
  }

  @Override
  public void onStart(boolean viewCreated) {
    super.onStart(viewCreated);

    // Your code here. Your view is available using mView and will not be null until next onStop()

    mInteractor.checkToken()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new DisposableCompletableObserver() {
          @Override
          public void onComplete() {
            if (mView != null) {
              mView.loginSuccessful();
            }
          }

          @Override
          public void onError(@io.reactivex.annotations.NonNull Throwable e) {

          }
        });
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

  @Override
  public void tryLogin(String userName, String password) {
    mInteractor.tryLogin(new User(userName, password))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new DisposableCompletableObserver() {
          @Override
          public void onComplete() {
            if (mView != null) {
              mView.loginSuccessful();
            }
          }

          @Override
          public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            if (mView != null) {
              mView.failure();
            }
          }
        });
  }
}