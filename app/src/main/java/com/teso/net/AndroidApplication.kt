package com.teso.net

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.teso.net.di.AppComponent
import com.teso.net.di.AppModule
import com.teso.net.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


class AndroidApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var component: AppComponent
    }

    private var defaultHandler: Thread.UncaughtExceptionHandler? = null

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        // Normal app init code...
        initExceptionHandler()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)

            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + "::Line:" + element.lineNumber + "::" + element.methodName + "()"
                }
            })

            val debugConfig = ImagePipelineConfig.newBuilder(this)
                    .setRequestListeners(HashSet<RequestListener>().apply { add(RequestLoggingListener()) })
                    .build()
            Fresco.initialize(this, debugConfig)
        } else {
            Fresco.initialize(this)
        }
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
        Timber.d("App started")
    }

    private fun initExceptionHandler() {
        if (defaultHandler == null) {
            defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Timber.e(e)
            defaultHandler?.uncaughtException(t, e)
        }
    }
}