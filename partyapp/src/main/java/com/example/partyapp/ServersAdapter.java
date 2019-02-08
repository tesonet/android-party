package com.example.partyapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServersAdapter extends BaseAdapter {

    ArrayList<Server> servers;
    Context context;

    public ServersAdapter(ArrayList<Server> serverArrayList, Context context) {
        servers = serverArrayList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return servers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_servers, parent, false);
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView distanceTextView  = (TextView) view.findViewById(R.id.distance);
        Server server = servers.get(position);

        nameTextView.setText(server.name);
        distanceTextView.setText(String.valueOf(server.distance));

        return view;
    }
}
