package lt.petraslabutis.testio.dagger

import dagger.Component
import lt.petraslabutis.testio.ViewModelsUnitTest
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class, AppModule::class, ViewModelModule::class])
interface TestComponent : ApplicationComponent {
    fun inject(test: ViewModelsUnitTest)
}