package lt.petraslabutis.testio

import android.os.Looper
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.securepreferences.SecurePreferences
import io.realm.Realm
import lt.petraslabutis.testio.dagger.*
import lt.petraslabutis.testio.viewmodels.AuthenticationViewModel
import lt.petraslabutis.testio.viewmodels.AuthenticationViewModel.Companion.AUTH_TOKEN
import lt.petraslabutis.testio.viewmodels.ServerListViewModel
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class ViewModelsUnitTest { //I Have no experience with unit tests :(

    @Inject
    lateinit var authenticationViewModel: AuthenticationViewModel
    @Inject
    lateinit var serverListViewModel: ServerListViewModel
    @Inject
    lateinit var securePreferences: SecurePreferences

    @Before
    fun init() {
        val appContext = InstrumentationRegistry.getTargetContext()

        DaggerTestComponent.builder()
            .appModule(AppModule(appContext))
            .apiModule(ApiModule())
            .storageModule(StorageModule())
            .build().also {
                it.inject(this)
            }

        if (Looper.myLooper() == null) {
            Looper.prepare()
        }
    }

    @Test
    fun test1AuthenticationViewModel() {
        authenticationViewModel.logout()
        authenticationViewModel.login(
            BuildConfig.TEST_LOGIN_CREDENTIALS_USERNAME,
            BuildConfig.TEST_LOGIN_CREDENTIALS_PASSWORD
        ).blockingSubscribe {
            assertTrue(it.token.isNotEmpty())
            assertTrue(authenticationViewModel.isLoggedIn())
        }
    }

    @Test
    fun test2ServerListViewModel() {
        serverListViewModel
            .refreshServerData()
            .toObservable<Unit>()
            .blockingSubscribe {
                serverListViewModel
                    .getServerList()
                    .blockingSubscribe { results ->
                        Realm.getDefaultInstance().executeTransaction {
                            assertTrue(results.isNotEmpty())
                        }
                    }
            }
    }

    @Test
    fun test3Logout() {
        authenticationViewModel.logout()
        assertTrue(securePreferences.getString(AUTH_TOKEN, "").isNullOrEmpty())
        assertFalse(authenticationViewModel.isLoggedIn())

        serverListViewModel
            .getServerList()
            .subscribe { results ->
                Realm.getDefaultInstance().executeTransaction {
                    assertTrue(results.isEmpty())
                }
            }
    }

}