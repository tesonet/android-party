package com.azzdorfrobotics.android.testio.di;

import android.content.Context;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.preferences.Key;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@SuppressWarnings("WeakerAccess") @Module public class ResourceModule {

    @Provides @Named(Key.API_URL) String provideApiUrl(Context context) {
        return context.getResources().getString(R.string.api_url);
    }
}
