package lajevski.radoslav.tesonetparty.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class AppNetwork {

    /** A name tag for this class. **/
    public static final String TAG = AppNetwork.class.getSimpleName();

    /**
     * Get the NetworkInfo object from the System's ConnectivityManager.
     *
     * @param context - the currently active Context
     * @return an object containing the information about the network connection
     */
    public static NetworkInfo getNetworkInfo(Context context){
        // Get the active network's information from the manager
        ConnectivityManager _cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Return the retrieved NetworkInfo object
        return _cm.getActiveNetworkInfo();
    }

    /**
     * Check if a network (Internet) connection of any type (WiFi, mobile data, etc.) is available and active (present).
     *
     * NOTE: does not evaluate the stability and quality of the connection.
     *
     * @param context - the currently active Context
     * @return true, if a network (Internet) connection is available and active (present). Otherwise false
     */
    public static boolean isNetworkConnectionPresent(Context context) {
        // Get the active network's information
        NetworkInfo _info = AppNetwork.getNetworkInfo(context);

        // If the network's information was retrieved successfully and there is a connection currently established,
        // then a network connection is present and active. Otherwise - not
        return (_info != null && _info.isConnected());
    }

}
