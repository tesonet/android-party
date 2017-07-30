package com.romiope.testapp;

import android.support.v7.app.AppCompatActivity;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription subscriptions;

    @Override
    protected void onStart() {
        super.onStart();
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onStop() {
        subscriptions.unsubscribe();
        super.onStop();
    }
}
