package com.azzdorfrobotics.android.testio.features.servers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.network.model.response.Server;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 03.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ViewHolder> {

    private List<Server> servers = new ArrayList<>();

    public ServerListAdapter(List<Server> servers) {
        this.servers = servers;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_item_server, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(servers.get(holder.getAdapterPosition()),
            holder.getAdapterPosition() + 1 == servers.size());
    }

    @Override public int getItemCount() {
        return servers.size();
    }

    public void setData(List<Server> servers){
        this.servers = servers;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name) TextView tvName;
        @BindView(R.id.tv_distance) TextView tvDistance;
        @BindView(R.id.v_divider) View vDivider;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindTo(Server server, boolean isLast) {
            tvName.setText(server.getName());
            tvDistance.setText(server.getDistance());
            vDivider.setVisibility(isLast ? View.GONE : View.VISIBLE);
        }
    }
}