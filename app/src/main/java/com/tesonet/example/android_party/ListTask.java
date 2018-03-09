package com.tesonet.example.android_party;

import android.os.AsyncTask;
import android.util.Log;

import com.tesonet.example.android_party.communication.ListHandler;
import com.tesonet.example.android_party.model.ExListResponse;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Vilius on 2018-03-09.
 */

public class ListTask extends AsyncTask<String, Void, ExListResponse> {
    private WeakReference<MainActivity> weakActivity;

    public ListTask(MainActivity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected ExListResponse doInBackground(String... urls) {
        ExListResponse listResponse = new ExListResponse();

        try {
            ListHandler handler = new ListHandler(weakActivity.get());
            listResponse = handler.getList();
        } catch (IOException e) {
            Log.e("ListT", "Error getting list", e);
        }
        return listResponse;
    }

    @Override
    protected void onPostExecute(ExListResponse response) {
        super.onPostExecute(response);

        weakActivity.get().handleExList(response);
    }
}
