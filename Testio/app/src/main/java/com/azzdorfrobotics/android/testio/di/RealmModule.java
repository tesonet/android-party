package com.azzdorfrobotics.android.testio.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import javax.inject.Singleton;

/**
 * Created on 04.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@SuppressWarnings("WeakerAccess") @Module public class RealmModule {

    @Singleton @Provides RealmConfiguration provideRealmConfiguration(Context context){
        Realm.init(context);
        return Realm.getDefaultConfiguration();
    }
}
