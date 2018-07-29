package lt.tesonet.ui.servers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.tesonet.R;
import lt.tesonet.model.Server;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ViewHolder> {

    private List<Server> data = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_server, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();

        Server server = data.get(position);
        viewHolder.name.setText(server.getName());
        viewHolder.distance.setText(context.getString(R.string.server_distance_pattern, server.getDistance()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Server> servers) {
        data.clear();
        data.addAll(servers);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.distance)
        TextView distance;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
