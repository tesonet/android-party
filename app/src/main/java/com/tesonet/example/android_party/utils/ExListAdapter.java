package com.tesonet.example.android_party.utils;

/**
 * Created by Vilius on 2018-03-08.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tesonet.example.android_party.MainActivity;
import com.tesonet.example.android_party.R;
import com.tesonet.example.android_party.model.ExListItem;


import java.util.ArrayList;
import java.util.Arrays;

public class ExListAdapter extends RecyclerView.Adapter<ExListAdapter.ViewHolder> {
    private ArrayList<ExListItem> exList = new ArrayList<>();
    private Context context;
    private MainActivity activity;

    public ExListAdapter(Context context, ArrayList<ExListItem> list) {
        this.exList = list;
        this.context = context;
        activity = (MainActivity) context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView distance;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.nameTxt);
            distance = v.findViewById(R.id.distTxt);
        }
    }

    @Override
    public ExListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_ex_list_item, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExListAdapter.ViewHolder holder, int i) {
        holder.name.setText(exList.get(i).getName());
        holder.distance.setText(String.valueOf(exList.get(i).getDistance()).concat(" km"));
    }

    @Override
    public int getItemCount() {
        if (exList != null) {
            return exList.size();
        } else {
            return 0;
        }
    }
}
