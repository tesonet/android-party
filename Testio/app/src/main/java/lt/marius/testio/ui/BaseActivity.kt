package lt.marius.testio.ui

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.WindowManager
import io.reactivex.disposables.CompositeDisposable
import lt.marius.testio.GlobalErrorEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by marius on 17.8.21.
 */
abstract class BaseActivity : LifecycleActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		                WindowManager.LayoutParams.FLAG_FULLSCREEN)

	}


	val disposable by lazy {
		CompositeDisposable()
	}


	override fun onStart() {
		super.onStart()
		EventBus.getDefault().register(this)
	}

	override fun onStop() {
		super.onStop()
		EventBus.getDefault().unregister(this)
		disposable.clear()
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when {
			item.itemId == android.R.id.home -> {
				finish()
				return true
			}
		}
		return super.onOptionsItemSelected(item)
	}

	private var mLastAlertDialog: AlertDialog? = null

	@Subscribe(threadMode = ThreadMode.MAIN)
	open fun onEvent(globalErrorEvent: GlobalErrorEvent) {
		val lastAlertDialog = mLastAlertDialog
		if (lastAlertDialog != null && lastAlertDialog.isShowing) {
			lastAlertDialog.dismiss()
			mLastAlertDialog = null
		}
		mLastAlertDialog = AlertDialog.Builder(this@BaseActivity)
				.setMessage(globalErrorEvent.message)
				.setNegativeButton(android.R.string.ok, null)
				.create()
		mLastAlertDialog?.show()
	}


}