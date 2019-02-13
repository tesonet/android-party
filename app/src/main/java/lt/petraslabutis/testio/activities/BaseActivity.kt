package lt.petraslabutis.testio.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity() {

    val disposables by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    abstract fun inject()
}