package lt.havefun.tesonetfunparty

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import lt.havefun.tesonetfunparty.events.LoggedInEvent
import lt.havefun.tesonetfunparty.events.LogoutEvent
import lt.havefun.tesonetfunparty.events.ServersLoadedEvent
import lt.havefun.tesonetfunparty.injections.components.ActivityComponent
import lt.havefun.tesonetfunparty.injections.components.DaggerActivityComponent
import lt.havefun.tesonetfunparty.injections.modules.ActivityModule
import lt.havefun.tesonetfunparty.injections.modules.ApiModule
import lt.havefun.tesonetfunparty.injections.modules.AppModule
import lt.havefun.tesonetfunparty.injections.modules.RoomModule
import lt.havefun.tesonetfunparty.ui.ToolbarView
import lt.havefun.tesonetfunparty.ui.login.LoginFragment
import lt.havefun.tesonetfunparty.ui.main.ServersFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainActivityViewModel.Factory

    @Inject
    lateinit var eventBus: EventBus

    lateinit var activityComponent: ActivityComponent
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var toolbar: ToolbarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        prepareActivityComponent()
        activityComponent.inject(this)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        toolbar = findViewById(R.id.toolbar)
        activityComponent.inject(toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            if (viewModel.hasToken()) {
                goToMainFragment()
            } else {
                goToLoginFragment()
            }
        }
    }

    private fun goToLoginFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.container, LoginFragment.newInstance())
            .commitNow()
    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.container, ServersFragment.newInstance())
            .commitNow()
    }

    override fun onStart() {
        super.onStart()
        eventBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        eventBus.unregister(this)
    }

    @Subscribe
    fun onEvent(event: LoggedInEvent) {
        Log.d(null, "event $event")
        goToMainFragment()
    }

    @Subscribe
    fun onEvent(event: LogoutEvent) {
        Log.d(null, "event $event")
        goToLoginFragment()
        hideToolbar()
        viewModel.logout()
    }

    @Subscribe
    fun onEvent(event: ServersLoadedEvent) {
        Log.d(null, "event $event")
        if (event.success) {
            showToolbar()
        } else {
            viewModel.logout()
        }
    }

    private fun prepareActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appModule(
                AppModule(
                    App.getApplication(this)
                )
            )
            .roomModule(
                RoomModule(
                    App.getApplication(this)
                )
            )
            .activityModule(
                ActivityModule(
                    this)
            )
            .apiModule(ApiModule())
            .build()
    }

    private fun showToolbar() {
        toolbar.visibility = View.VISIBLE
    }

    private fun hideToolbar() {
        toolbar.visibility = View.GONE
    }
}