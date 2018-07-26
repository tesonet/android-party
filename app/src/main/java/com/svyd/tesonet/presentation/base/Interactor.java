package com.svyd.tesonet.presentation.base;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

@SuppressWarnings("unchecked")
public abstract class Interactor<T> {

    private Subscription mPostSubscription = Subscriptions.empty();

    @SuppressWarnings("unchecked")
    public void execute(T _data, Observer _subscriber) {
        mPostSubscription = buildObservable(_data)
                .subscribeOn(Schedulers.newThread())
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

    protected abstract Observable buildObservable(T _data);

    public void unSubscribe() {
        if (!mPostSubscription.isUnsubscribed())
            mPostSubscription.unsubscribe();
    }

}
