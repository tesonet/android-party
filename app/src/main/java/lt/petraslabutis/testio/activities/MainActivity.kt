package lt.petraslabutis.testio.activities

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import io.reactivex.rxkotlin.addTo
import lt.petraslabutis.testio.R
import lt.petraslabutis.testio.TestioApplication
import lt.petraslabutis.testio.fragments.LoginFragment
import lt.petraslabutis.testio.fragments.ServerListFragment
import lt.petraslabutis.testio.viewmodels.AuthenticationViewModel
import lt.petraslabutis.testio.viewmodels.NavigationViewModel
import lt.petraslabutis.testio.viewmodels.NavigationViewModel.Type.REPLACE
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var navigationViewModel: NavigationViewModel
    @Inject
    lateinit var authenticationViewModel: AuthenticationViewModel

    override fun inject() {
        TestioApplication.applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = if (authenticationViewModel.isLoggedIn()) ServerListFragment() else LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.mainActivity, fragment).commit()
    }

    override fun onStart() {
        super.onStart()

        navigationViewModel
            .transactionObservable
            .subscribe {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .apply {
                        when (it.type) {
                            REPLACE -> replace(R.id.mainActivity, it.fragment)
                        }
                    }.commit()
            }.addTo(disposables)
    }

}
