package com.yegor.tesonet.partyapp.ui.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yegor.tesonet.partyapp.R;
import com.yegor.tesonet.partyapp.model.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for servers list
 */
class ServersListAdapter extends RecyclerView.Adapter<ServerViewHolder> {

    private Context mContext;
    private List<Server> mServers = new ArrayList<>();

    ServersListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.servers_list_item, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServerViewHolder holder, int position) {
        Server server = mServers.get(position);
        holder.name.setText(server.getName());
        holder.distance.setText(getDistanceRepresentation(server.getDistance()));
    }

    @Override
    public int getItemCount() {
        return mServers.size();
    }

    void setServers(List<Server> servers) {
        mServers = servers;
        notifyDataSetChanged();
    }

    private String getDistanceRepresentation(String distance){
        return distance + " km";
    }
}

