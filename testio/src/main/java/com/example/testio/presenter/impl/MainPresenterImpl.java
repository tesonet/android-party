package com.example.testio.presenter.impl;

import android.support.annotation.NonNull;
import com.example.testio.interactor.MainInteractor;
import com.example.testio.models.Server;
import com.example.testio.models.Token;
import com.example.testio.presenter.MainPresenter;
import com.example.testio.view.MainView;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
  @NonNull
  private final MainInteractor mInteractor;
  /**
   * The interactor
   */

  List<Server> serverList;

  // The view is available using the mView variable

  @Inject
  public MainPresenterImpl(@NonNull MainInteractor interactor) {
    mInteractor = interactor;
  }

  @Override
  public void onStart(boolean viewCreated) {
    super.onStart(viewCreated);

    if (serverList == null) {
      mInteractor.getToken()
          .doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable)
                throws Exception {
              if (mView != null) {
                mView.noToken();
              }
            }
          })
          .flatMap(new Function<Token, SingleSource<List<Server>>>() {
            @Override
            public SingleSource<List<Server>> apply(@io.reactivex.annotations.NonNull Token token)
                throws Exception {
              return mInteractor.getServerList(token.getToken());
            }
          })
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new DisposableSingleObserver<List<Server>>() {
            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull List<Server> servers) {
              serverList = servers;
              if (mView != null) mView.getServers(serverList);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
              if (mView != null) mView.onError();
            }
          });
    } else {
      if (mView != null) {
        mView.getServers(serverList);
      }
    }
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

  @Override
  public void releaseToken() {
    mInteractor.releaseToken()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new DisposableCompletableObserver() {
          @Override
          public void onComplete() {
            if (mView != null) {
              mView.noToken();
            }
          }

          @Override
          public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            Timber.d("onError %s", e.toString());
          }
        });
  }
}