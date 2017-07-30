package com.romiope.testapp.network.v1.wrappers;

import retrofit2.Retrofit;

abstract class BaseWrapper {

    protected Retrofit retrofit;

    public BaseWrapper(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
