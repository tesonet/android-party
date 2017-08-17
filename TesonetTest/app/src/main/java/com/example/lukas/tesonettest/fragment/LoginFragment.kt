package com.example.lukas.tesonettest.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.example.lukas.tesonettest.R
import com.example.lukas.tesonettest.model.Login
import com.example.lukas.tesonettest.model.Server
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import lt.topocentras.android.Prefs
import java.util.concurrent.TimeUnit


/**
 * Created by lukas on 17.8.17.
 */
class LoginFragment : BaseFragment() {
	companion object {
		fun getInstance(): LoginFragment {
			val fragment = LoginFragment()
			return fragment
		}
	}

	override val layoutId: Int
		get() = R.layout.fragment_login


	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupFields()
		val buttonObservable = RxView.clicks(login_button)
		val usernameObservable = RxTextView.textChanges(login_username)
		val passwordObservable = RxTextView.textChanges(login_password)

		val isSignInEnabled: Observable<Boolean> = Observable
				.combineLatest(usernameObservable,
				               passwordObservable,
				               BiFunction { u, p -> u.isNotEmpty() && p.isNotEmpty() })
		isSignInEnabled.subscribe { login_button.isEnabled = it }
		buttonObservable.subscribe(this::onLoginClick)
		if (Prefs.authorization != null){
			fetchServers()
		}
	}

	fun setupFields() {
		login_button.isEnabled = false
		login_password.typeface = Typeface.DEFAULT
		login_password.transformationMethod = PasswordTransformationMethod()
	}

	fun onLoginClick(t: Any) {
		Login(login_username.text.toString(), login_password.text.toString())
				.login()
				.delay(2000L,TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe {
					showProgress(true)
					setLoadingText(R.string.loading_text_login)
				}
				.doOnError {
					showProgress(false)
				}
				.subscribe({
					           it.save()
					           fetchServers()
				           }, Throwable::printStackTrace)
				.addTo(disposable)
	}

	fun fetchServers() {
		Server.getServers()
				//delay to make the progress visible
				.delay(2000L,TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe {
					showProgress(true)
					setLoadingText(R.string.loading_text_servers)
				}
				.doOnError {
					showProgress(false)
				}
				.subscribe({
					           changeFragment(ServerListFragment.getInstance(it), false)
				           }, Throwable::printStackTrace)
				.addTo(disposable)
	}

	fun showProgress(show: Boolean) {
		login_logo.visibility = if (show) View.GONE else View.VISIBLE
		login_container.visibility = if (show) View.GONE else View.VISIBLE
		login_progress_container.visibility = if (show) View.VISIBLE else View.GONE
	}

	fun setLoadingText(resId: Int) {
		login_progress_text.text = getString(resId)
	}
}