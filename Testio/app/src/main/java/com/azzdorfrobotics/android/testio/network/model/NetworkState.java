package com.azzdorfrobotics.android.testio.network.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import javax.inject.Inject;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class NetworkState {

    public enum Connect {
        MOBILE, WIFI, CONNECTED, NONE;
    }

    private final ConnectivityManager connectivityManager;

    @Inject public NetworkState(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public Connect checkConnect() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()
            ? Connect.CONNECTED : Connect.NONE;
    }

    public Connect checkConnectionType() {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConnected =
            networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConnected =
            networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
        return isWifiConnected ? Connect.WIFI : isMobileConnected ? Connect.MOBILE : Connect.NONE;
    }

    public boolean checkWifiConnected() {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }
}
