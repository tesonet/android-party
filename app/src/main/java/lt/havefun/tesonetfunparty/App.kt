package lt.havefun.tesonetfunparty

import android.app.Application
import android.content.Context
import lt.havefun.tesonetfunparty.injections.components.AppComponent
import lt.havefun.tesonetfunparty.injections.components.DaggerAppComponent

class App: Application() {

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        if (component == null) {
            component = DaggerAppComponent.builder()
                .build()
        }

        component?.inject(this)
    }

    companion object {
        fun getApplication(context: Context): App = context.applicationContext as App
    }
}
