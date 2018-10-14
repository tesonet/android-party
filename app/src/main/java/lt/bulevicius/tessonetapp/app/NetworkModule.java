package lt.bulevicius.tessonetapp.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lt.bulevicius.tessonetapp.BuildConfig;
import lt.bulevicius.tessonetapp.app.utils.NetworkSchedulerProvider;
import lt.bulevicius.tessonetapp.app.utils.SchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

@Module
final class NetworkModule {

    @Provides
    @Singleton
    final Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .client(client)
                                     .baseUrl(BuildConfig.API)
                                     .build();
    }

    @Provides
    @Singleton
    final TessonetApi api(Retrofit retrofit) {
        return retrofit.create(TessonetApi.class);
    }

    @Provides
    @Singleton
    final OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    @Singleton
    final HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(BODY);
    }

    @Provides
    @Singleton
    final SchedulerProvider networkSchedulerProvider() {
        return new NetworkSchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
