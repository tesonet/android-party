package lt.topocentras.android

import android.app.Application
import lt.topocentras.android.api.Api

/**
 * Created by marius on 17.2.10.
 */
class App : Application() {
	override fun onCreate() {
		super.onCreate()
		Prefs.context = applicationContext
		Api.context = applicationContext
	}
}
