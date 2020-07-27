package lt.havefun.tesonetfunparty.injections.components

import dagger.Component
import lt.havefun.tesonetfunparty.App
import lt.havefun.tesonetfunparty.injections.modules.ApiModule
import lt.havefun.tesonetfunparty.injections.modules.AppModule
import lt.havefun.tesonetfunparty.injections.modules.PreferencesModule
import lt.havefun.tesonetfunparty.injections.modules.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    PreferencesModule::class,
    RoomModule::class
])
interface AppComponent {
    fun inject(application: App)
}
