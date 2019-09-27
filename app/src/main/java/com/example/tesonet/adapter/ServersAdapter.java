package com.example.tesonet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesonet.R;
import com.example.tesonet.database.models.Server;

import java.util.List;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ServerViewHolder> {
    private Context context;
    private List<Server> serverList;

    public ServersAdapter(Context context, List<Server> serverList) {
        this.context = context;
        this.serverList = serverList;
    }

    @NonNull
    @Override
    public ServersAdapter.ServerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_listitem, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServersAdapter.ServerViewHolder holder, int position) {
        holder.itemView.setTag(serverList.get(position));

        Server server = serverList.get(position);

        holder.countryTextView.setText(server.getName());
        holder.distanceTextView.setText(server.getDistance() + " km");
    }

    @Override
    public int getItemCount() {
        return serverList.size();
    }

    class ServerViewHolder extends RecyclerView.ViewHolder {
        TextView countryTextView;
        TextView distanceTextView;


        public ServerViewHolder(@NonNull View itemView) {
            super(itemView);

            countryTextView = itemView.findViewById(R.id.locationTextView);
            distanceTextView = itemView.findViewById(R.id.distanceTextView);
        }
    }
}
