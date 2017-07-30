package com.romiope.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romiope.testapp.network.entities.ServerResponse;

import java.util.Collections;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ServerLineHolder> {

    private List<ServerResponse> data = Collections.emptyList();

    public void addAll(List<ServerResponse> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ServerLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServerLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(ServerLineHolder holder, int position) {
        final ServerResponse item = data.get(position);

        holder.name.setText(item.name);
        holder.distance.setText(item.distance);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ServerLineHolder extends RecyclerView.ViewHolder {

        final TextView name;
        final TextView distance;

        ServerLineHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            distance = itemView.findViewById(R.id.tv_distance);
        }
    }
}
