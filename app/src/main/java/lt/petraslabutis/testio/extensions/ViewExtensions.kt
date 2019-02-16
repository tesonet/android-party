package lt.petraslabutis.testio.extensions

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun View.onClick(action: () -> Unit): Disposable =
    RxView
        .clicks(this)
        .throttleFirst(200, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { action() }

fun View.gone() {
    this.visibility = View.GONE
}
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.setRightPadding(size: Int) = this.setPadding(this.paddingLeft, this.paddingTop, size, this.paddingBottom)
fun View.setTopPadding(size: Int) = this.setPadding(this.paddingLeft, size, this.paddingRight, this.paddingBottom)