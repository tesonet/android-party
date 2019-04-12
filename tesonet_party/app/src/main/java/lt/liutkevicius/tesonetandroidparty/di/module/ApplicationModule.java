package lt.liutkevicius.tesonetandroidparty.di.module;

import android.content.Context;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import lt.liutkevicius.tesonetandroidparty.storage.ISharedPrefs;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;

import javax.inject.Singleton;

@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ISharedPrefs provideSharedPrefs(Context context, Gson gson){
        return new SharedPrefs(context, gson);
    }
}
