package com.example.justinaszableckisand.androidparty.Base;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void onError(int errorResourceId);

    void onError(String errorMessage);
}
