package com.tesonet.example.android_party;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tesonet.example.android_party.utils.ExListAdapter;
import com.tesonet.example.android_party.utils.Preferences;

import java.util.ArrayList;

/**
 * Created by Vilius on 2018-03-07.
 */

public class ExListFragment extends Fragment {

    private MainActivity activity;
    private RecyclerView exListView;
    private ExListAdapter listAdapter;

    public ExListFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        ImageView logout = v.findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.storeTokenValue(activity, null);
                Preferences.storeExList(activity, null);
                activity.showLoginFrag();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(activity);
        exListView = v.findViewById(R.id.recycler);
        exListView.setLayoutManager(llm);

        listAdapter = new ExListAdapter(activity, activity.getExList());
        exListView.setAdapter(listAdapter);
        exListView.setHasFixedSize(true);
        exListView.setNestedScrollingEnabled(false);

        return v;
    }
}
