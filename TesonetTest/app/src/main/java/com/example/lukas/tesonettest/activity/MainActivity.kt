package com.example.lukas.tesonettest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.lukas.tesonettest.GlobalErrorEvent
import com.example.lukas.tesonettest.R
import com.example.lukas.tesonettest.UnauthorizedEvent
import com.example.lukas.tesonettest.fragment.BaseFragment
import com.example.lukas.tesonettest.fragment.LoginFragment
import lt.topocentras.android.Prefs
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		changeFragment(LoginFragment.getInstance(),false)
	}

	override fun onStart() {
		super.onStart()
		EventBus.getDefault().register(this)
	}

	override fun onStop() {
		EventBus.getDefault().unregister(this)
		super.onStop()
	}

	fun changeFragment(fragment: BaseFragment, addToBackStack: Boolean = true) {
		val manager = supportFragmentManager
		val ft = manager.beginTransaction()
		ft.setCustomAnimations(R.anim.fade_in,
		                       R.anim.fade_out)
		if (addToBackStack) {
			ft.addToBackStack(fragment.javaClass.canonicalName)
		}
		ft.replace(R.id.main_container, fragment)
		ft.commit()
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onEvent(event: UnauthorizedEvent) {
		Prefs.authorization = null
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onEvent(event: GlobalErrorEvent) {
		Toast.makeText(this, event.error, Toast.LENGTH_SHORT).show()
	}

}
