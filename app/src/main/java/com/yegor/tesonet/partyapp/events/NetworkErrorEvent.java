package com.yegor.tesonet.partyapp.events;

/**
 * Event to notify that network error occured
 */
public class NetworkErrorEvent {

    private String mErrorMessage;

    public NetworkErrorEvent(String error){
        mErrorMessage = error;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
