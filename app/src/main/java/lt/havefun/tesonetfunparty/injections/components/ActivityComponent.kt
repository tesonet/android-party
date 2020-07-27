package lt.havefun.tesonetfunparty.injections.components

import dagger.Component
import lt.havefun.tesonetfunparty.MainActivity
import lt.havefun.tesonetfunparty.injections.modules.ActivityModule
import lt.havefun.tesonetfunparty.injections.modules.ApiModule
import lt.havefun.tesonetfunparty.injections.modules.AppModule
import lt.havefun.tesonetfunparty.injections.modules.RoomModule
import lt.havefun.tesonetfunparty.injections.modules.LoginModule
import lt.havefun.tesonetfunparty.injections.modules.MainModule
import lt.havefun.tesonetfunparty.injections.modules.PreferencesModule
import lt.havefun.tesonetfunparty.ui.ToolbarView
import lt.havefun.tesonetfunparty.ui.login.LoginFragment
import lt.havefun.tesonetfunparty.ui.main.ServersFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RoomModule::class,
    ActivityModule::class,
    ApiModule::class,
    LoginModule::class,
    MainModule::class,
    PreferencesModule::class
])

interface ActivityComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: ServersFragment)

    fun inject(fragment: LoginFragment)

    fun inject(View: ToolbarView)
}