package lt.bulevicius.tessonetapp.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

@Module
final class NetworkModule {

    @Provides
    @Singleton
    final Retrofit retrofit() {
        return new Retrofit.Builder().build();
    }

    @Provides
    @Singleton
    final OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    final HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(BODY);
    }
}
