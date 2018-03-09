package com.tesonet.example.android_party.utils;

import android.content.Context;
import android.util.Log;

import com.tesonet.example.android_party.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Vilius on 2018-03-08.
 */

public final class AppProperties {
    private AppProperties() {}

    private static final String HOST = "host";

    private static Properties properties;

    public static Properties getProperties(Context context) {
        if (properties == null) {
            properties = new Properties();
            InputStream in = context.getResources().openRawResource(R.raw.app);
            try {
                properties.load(in);
            } catch (IOException e) {
                Log.e("Props", "Failed to read properties", e);
            }
        }
        return properties;
    }

    public static String getHost(Context context) {
        return getProperties(context).getProperty(HOST);
    }
}
