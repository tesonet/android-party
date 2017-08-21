package lt.marius.testio.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_login.*
import lt.marius.testio.LoginEvent
import lt.marius.testio.R
import lt.marius.testio.api.ApiAppServiceFactory
import lt.marius.testio.model.LoginRequestBody
import lt.marius.testio.model.Server
import lt.marius.testio.persistance.UserPreferences
import lt.marius.testio.viewModel.ServersViewModel
import org.greenrobot.eventbus.Subscribe

class MainActivity : BaseActivity() {
	private val appService by lazy {
		ApiAppServiceFactory().createAppService(this)
	}

	private val userPrefs by lazy {
		UserPreferences(this)
	}

	private val loginFragment by lazy {
		val oldFragment = supportFragmentManager.findFragmentByTag("login_fragment")

		//after activity recreation - we can still reuse old fragment
		if (oldFragment == null) {
			val newFragment = LoginFragment()
			supportFragmentManager
					.beginTransaction()
					.add(R.id.mainFragmentContainer, newFragment, "login_fragment")
					.commit()
			newFragment
		} else {
			oldFragment
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		                WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.activity_login)


		loginFragment
		ViewModelProviders
				.of(this)
				.get(ServersViewModel::class.java)
				.servers
				.observe(this, Observer {
					observeServerList(it)
				})
		userPrefs.authorization?.let {
			getServers()
		}

	}

	private fun observeServerList(servers: List<Server>?) {
		if (servers == null) {
			supportFragmentManager.findFragmentByTag("server_list_fragment")?.let {
				supportFragmentManager.beginTransaction().remove(it).commit()
			}
			if (supportFragmentManager.findFragmentByTag("login_fragment") == null) {
				supportFragmentManager
						.beginTransaction()
						.add(R.id.mainFragmentContainer, loginFragment, "login_fragment")
						.commit()

			}
		} else {
			supportFragmentManager.beginTransaction().remove(loginFragment).commit()
			if (supportFragmentManager.findFragmentByTag("server_list_fragment") == null) {
				supportFragmentManager
						.beginTransaction()
						.add(R.id.mainFragmentContainer,
						     ServerListFragment(),
						     "server_list_fragment")
						.commit()
			}
		}
	}


	@Subscribe
	fun onEvent(event: LoginEvent) {
		showProgress(getString(R.string.login_progress_message_login))
		val loginRequest = LoginRequestBody(username = event.username,
		                                    password = event.password)
		appService.login(loginRequest)
				.doOnError { hideProgress() }
				.subscribe({
					           userPrefs.authorization = it.token
					           getServers()
				           }, {
					           //todo hmmmz
				           })
				.addTo(disposable)
	}

	private fun getServers() {
		showProgress(getString(R.string.login_progress_message_fetchList))
		appService.getServers()
				.doOnError { hideProgress() }
				.subscribe({
					           hideProgress()
					           ViewModelProviders
							           .of(this)
							           .get(ServersViewModel::class.java)
							           .servers
							           .postValue(it)
				           }, {
					           //todo hmmmz
				           })
				.addTo(disposable)
	}

	private fun showProgress(message: String? = null) {
		mainProgressContainer.visibility = View.VISIBLE
		mainFragmentContainer.visibility = View.GONE
		mainProgressMessage.text = message
	}

	private fun hideProgress() {
		mainProgressContainer.visibility = View.GONE
		mainFragmentContainer.visibility = View.VISIBLE
	}


}

