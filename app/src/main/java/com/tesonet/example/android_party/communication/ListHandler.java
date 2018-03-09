package com.tesonet.example.android_party.communication;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tesonet.example.android_party.model.ExListItem;
import com.tesonet.example.android_party.model.ExListResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vilius on 2018-03-09.
 */

public class ListHandler extends BaseOkHttpClient {
    private static final String METHOD = "servers";

    public ListHandler(Context context) {
        super(context);
        setEndpoint(METHOD);
    }

    public ExListResponse getList() throws IOException {
        Request request = buildGetRequest();
        ExListResponse listResponse = new ExListResponse();
        String json = null;
        try {
            Response response = client.newCall(request).execute();
            String status = response.message() + "(" + response.code() + ")";
            if (response.isSuccessful()) {
                json = response.body().string();
                Type listType = new TypeToken<ArrayList<ExListItem>>(){}.getType();
                ArrayList<ExListItem> retList = new Gson().fromJson(json, listType);
                listResponse.setList(retList);
            } else {
                throw new IOException("List query failed with status: " + status);
            }
        } catch (IOException e) {
            throw new IOException("List request failed", e);
        } catch (JsonSyntaxException e) {
            throw new IOException("Failed to parse JSON: " + json, e);
        }
        return listResponse;
    }
}
