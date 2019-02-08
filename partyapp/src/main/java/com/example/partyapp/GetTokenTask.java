package com.example.partyapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetTokenTask extends AsyncTask<String, Void, String> {

    private static String baseUrl = "http://playground.tesonet.lt/v1/";

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public GetTokenTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    @Override
    protected String doInBackground(String... args) {
        try {


            URL url = new URL(baseUrl + "tokens");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            OutputStream os = null;
            os = httpCon.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            JSONObject body = new JSONObject();
            try {
                body.put("username", args[0]);
                body.put("password", args[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            osw.write(body.toString());
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream
            httpCon.connect();

            //read the inputstream and print it
            String result;
            int code = httpCon.getResponseCode();
            String msg = httpCon.getResponseMessage();
            InputStream is = httpCon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result2 = bis.read();
            while (result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();
            }
            result = buf.toString();
            return result;
        } catch (Exception e) {
            int a = 4;
            e.printStackTrace();
        }
        return "";
    }
}
