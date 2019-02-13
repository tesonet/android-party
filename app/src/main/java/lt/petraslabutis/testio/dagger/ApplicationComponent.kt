package lt.petraslabutis.testio.dagger

import dagger.Component
import lt.petraslabutis.testio.TestioApplication
import lt.petraslabutis.testio.fragments.BaseFragment
import lt.petraslabutis.testio.fragments.LoginFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class, AppModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: TestioApplication)
    fun inject(fragment: BaseFragment)
    fun inject(fragment: LoginFragment)
}