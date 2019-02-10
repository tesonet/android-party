package lt.petraslabutis.tesonetapplication.dagger

import dagger.Component
import lt.petraslabutis.tesonetapplication.TesonetApplication
import lt.petraslabutis.tesonetapplication.managers.AuthenticationManager
import lt.petraslabutis.tesonetapplication.managers.ServerDataManager
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class])
interface ApplicationComponent {

    fun authenticationManager(): AuthenticationManager
    fun serverDataManager(): ServerDataManager

    fun inject(application: TesonetApplication)
}