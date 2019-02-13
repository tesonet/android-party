package lt.petraslabutis.testio.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun EditText.onKeyboardDoneClick(action: () -> Unit): Disposable {
    val subject = PublishSubject.create<Any>()
    this.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            subject.onNext(0)
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
    return subject
        .hide()
        .throttleFirst(200, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { action() }
}