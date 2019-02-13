package lt.petraslabutis.testio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import lt.petraslabutis.testio.TestioApplication

abstract class BaseFragment: Fragment() {

    val disposables by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TestioApplication.applicationComponent.inject(this)
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    abstract fun inject()

}