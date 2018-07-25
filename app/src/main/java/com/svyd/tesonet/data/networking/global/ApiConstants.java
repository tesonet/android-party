package com.svyd.tesonet.data.networking.global;

import okhttp3.logging.HttpLoggingInterceptor;

public final class ApiConstants {

    private ApiConstants() {

    }

    private static final String HTTPS_PREFIX = "https://";
    private static final String LIVE_INSTANCE = "xyz.com";

    public static final String API_ENDPOINT = HTTPS_PREFIX + LIVE_INSTANCE;

    public static final HttpLoggingInterceptor.Level LOGGING_LEVEL = HttpLoggingInterceptor.Level.BASIC;

    public static final int CONNECTION_TIME_OUT = 30;
    public static final int READ_TIME_OUT = 30;
    public static final int WRITE_TIME_OUT = 30;

}
