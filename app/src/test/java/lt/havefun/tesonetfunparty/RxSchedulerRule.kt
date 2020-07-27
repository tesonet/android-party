package lt.havefun.tesonetfunparty

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxSchedulerRule : TestRule {

    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { SCHEDULER }
                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler { SCHEDULER }
                RxJavaPlugins.setNewThreadSchedulerHandler { SCHEDULER }
                RxJavaPlugins.setComputationSchedulerHandler { SCHEDULER }
                base.evaluate()
            }
        }

    companion object {
        private val SCHEDULER = Schedulers.trampoline()
    }
}