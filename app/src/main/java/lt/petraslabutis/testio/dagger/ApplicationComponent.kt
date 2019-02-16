package lt.petraslabutis.testio.dagger

import dagger.Component
import lt.petraslabutis.testio.activities.MainActivity
import lt.petraslabutis.testio.fragments.BaseFragment
import lt.petraslabutis.testio.fragments.LoginFragment
import lt.petraslabutis.testio.fragments.ServerListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class, AppModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: BaseFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ServerListFragment)
    fun inject(activity: MainActivity)
}