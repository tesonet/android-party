package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject
import lt.petraslabutis.testio.entities.NavigationEntity
import lt.petraslabutis.testio.fragments.BaseFragment
import javax.inject.Singleton

@Singleton
class NavigationViewModel : ViewModel() {

    enum class Type {
        REPLACE
    }

    private val transactionSubject = PublishSubject.create<NavigationEntity>()
    val transactionObservable by lazy { transactionSubject.hide() }

    fun replaceTopFragment(fragment: BaseFragment) {
        transactionSubject.onNext(NavigationEntity(fragment, Type.REPLACE))
    }

    //Todo: add logic for pushing and removing fragments

}