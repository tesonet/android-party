package com.example.alex.partyapp.servers;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alex.partyapp.R;
import com.example.alex.partyapp.data.Server;

import java.util.ArrayList;
import java.util.List;

public class ServersAdapter extends RecyclerView.Adapter<ServerViewHolder> {
    private List<Server> data = new ArrayList<>();

    public void setData(List<Server> data) {
        this.data = data;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        return new ServerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.server_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ServerViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}