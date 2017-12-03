package com.tesonet.justas.androidparty;

import com.android.volley.RequestQueue;

public class HttpClient {
    private String baseUrl = "http://playground.tesonet.lt/v1/";
    private RequestQueue mQueue;
    public HttpClient(RequestQueue queue) { mQueue = queue; }
    public void request(TesonetRequest reqObj) { mQueue.add(reqObj.request()); }
    public TesonetRequest createRequest(String url, int method, ResponseListener responseListener){
        return new TesonetRequest(baseUrl + url, method, responseListener);
    }
}
