package com.njakawaii.tesonet.network;

import android.util.Log;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.HttpException;


public class RetryWithDelay
        implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int _maxRetries;
    private final int _retryDelayMillis;
    private int _retryCount;

    public RetryWithDelay(final int maxRetries, final int retryDelayMillis) {
        _maxRetries = maxRetries;
        _retryDelayMillis = retryDelayMillis;
        _retryCount = 0;
    }

    // this is a notificationhandler, all that is cared about here
    // is the emission "type" not emission "content"
    // only onNext triggers a re-subscription (onError + onComplete kills it)



    @Override
    public Observable<?> apply(Observable<? extends Throwable> inputObservable) {

        // it is critical to use inputObservable in the chain for the result
        // ignoring it and doing your own thing will break the sequence

        return inputObservable.flatMap(new Function<Throwable, Observable<?>>() {
            @Override
            public Observable<?> apply(Throwable throwable) {
                if (throwable instanceof HttpException){
                    if (((HttpException) throwable).code()!= HttpURLConnection.HTTP_UNAUTHORIZED) {
                        return Observable.error(throwable);
                    }
                }
                if (++_retryCount < _maxRetries) {

                    // When this Observable calls onNext, the original
                    // Observable will be retried (i.e. re-subscribed)

                    Log.d("Retrying in %d ms", ""+_retryCount * _retryDelayMillis);

                    return Observable.timer(_retryCount * _retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                }
                // Max retries hit. Pass an error so the chain is forcibly completed
                // only onNext triggers a re-subscription (onError + onComplete kills it)
                return Observable.error(throwable);
            }
        });
    }
}
