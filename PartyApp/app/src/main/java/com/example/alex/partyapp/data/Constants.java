package com.example.alex.partyapp.data;

public interface Constants {
    enum Status {
        LOADING,
        LOADED,
        LOGGING,
        LOGGED_IN,
        LOGGED_OUT
    }

    enum Error{
        FAILED,
        NETWORK_ERROR,
        OFFLINE_RESULTS,
        AUTHORIZATION_FAILED,
        NONE
    }
}
