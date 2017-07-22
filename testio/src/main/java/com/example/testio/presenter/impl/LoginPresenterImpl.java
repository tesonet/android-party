package com.example.testio.presenter.impl;

import android.support.annotation.NonNull;
import com.example.testio.interactor.LoginInteractor;
import com.example.testio.models.Server;
import com.example.testio.models.Token;
import com.example.testio.models.User;
import com.example.testio.presenter.LoginPresenter;
import com.example.testio.view.LoginView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

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

    //// TODO: 7/22/17 for testing
    mInteractor.getToken(new User("tesonet", "partyanimal"))
        .flatMap(new Function<Token, Observable<List<Server>>>() {
          @Override
          public Observable<List<Server>> apply(@io.reactivex.annotations.NonNull Token s)
              throws Exception {
            return mInteractor.getServersList(s.getToken());
          }
        })
        .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        .subscribe(new DisposableObserver<List<Server>>() {
          @Override
          public void onNext(@io.reactivex.annotations.NonNull List<Server> servers) {
            if (servers.size() != 0) {
              for (Server s : servers) {
                Timber.d(s.toString());
              }
            } else {
              Timber.d("No servers found");
            }
          }

          @Override
          public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            Timber.d("RX error %s", e.toString());
          }

          @Override
          public void onComplete() {
            Timber.d("onComplete");
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
}