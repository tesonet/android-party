package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import lt.petraslabutis.testio.entities.NavigationItem
import lt.petraslabutis.testio.fragments.BaseFragment
import javax.inject.Singleton

@Singleton
class NavigationViewModel : ViewModel() {

    enum class Type {
        REPLACE
    }

    private val transactionSubject = PublishSubject.create<NavigationItem>()
    val transactionObservable: Observable<NavigationItem> by lazy { transactionSubject.hide() }

    fun replaceTopFragment(fragment: BaseFragment) {
        transactionSubject.onNext(NavigationItem(fragment, Type.REPLACE))
    }

}