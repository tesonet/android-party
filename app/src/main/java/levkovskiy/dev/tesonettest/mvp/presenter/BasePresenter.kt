package levkovskiy.dev.tesonettest.mvp.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter {

  private val disposables = CompositeDisposable()

  protected fun unsubscribeOnDestroy(disposable: Disposable) {
    disposables.add(disposable)
  }


}