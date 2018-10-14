package lt.bulevicius.tessonetapp.app;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import lt.bulevicius.tessonetapp.storage.SharedPreferenceProvider;
import timber.log.Timber;

@Module
@SuppressWarnings("all")
public final class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public final Context context() {
        return context;
    }

    @Provides
    @Singleton
    public final Timber.Tree timber() {
        return new Timber.DebugTree();
    }

    @Provides
    @Singleton
    public final LocalDataProvider localDataProvider(SharedPreferences sharedPreferences) {
        return new SharedPreferenceProvider(sharedPreferences);
    }

    @Provides
    @Singleton
    public final SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(
                "lt.bulevicius.tessonetapp", Context.MODE_PRIVATE);
    }
}
