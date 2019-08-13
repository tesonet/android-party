package lt.tesonet.tesoapp;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import lt.tesonet.tesoapp.Models.Server;

public class TesoApp extends Application {

    private RequestQueue requestQueue;
    private static TesoApp appInstance;
    public static String authToken="";
    public final static String tokenUrl = "http://playground.tesonet.lt/v1/tokens";
    public final static String serverUrl = "http://playground.tesonet.lt/v1/servers";
    public static Server[] serverList;

    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static synchronized TesoApp getInstance() {
        return appInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }

    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }
}
