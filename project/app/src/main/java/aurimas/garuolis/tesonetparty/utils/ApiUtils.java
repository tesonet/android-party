package aurimas.garuolis.tesonetparty.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.MediaType;

/**
 * Created by Aurimas on 2018.02.05.
 */

public class ApiUtils {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String API_URL_BASE    =  "http://playground.tesonet.lt/v1/";
    public static final String API_URL_TOKEN   =  API_URL_BASE + "tokens";
    public static final String API_URL_SERVERS =  API_URL_BASE + "servers";

    public static boolean HasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
