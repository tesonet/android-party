package com.tesonet.justas.androidparty;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class TesonetRequest {

    private HashMap<String, String> mHeaders = null;
    private String mParameters = null;
    private ResponseListener mListener;
    private String mUrl;
    private int mMethod;
    public TesonetRequest(String url, int method, ResponseListener responseListener) {
        mListener = responseListener;
        mUrl = url;
        mMethod = method;
    }

    public TesonetRequest setHeader(HashMap<String, String> map) {
        mHeaders = map;
        return this;
    }

    public TesonetRequest setParameters(String params) {
        mParameters = params;
        return this;
    }

    public StringRequest request() {
        StringRequest stringRequest = new StringRequest(mMethod, mUrl, mListener, mListener) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mParameters == null ? null : mParameters.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(mHeaders != null) {
                    return mHeaders;
                }
                else {
                    return super.getHeaders();
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return Response.success(parseToString(response), HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        return stringRequest;
    }

    private String parseToString(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return parsed;
    }
}
