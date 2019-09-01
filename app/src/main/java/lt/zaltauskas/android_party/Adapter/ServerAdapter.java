package lt.zaltauskas.android_party.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lt.zaltauskas.android_party.Model.Server;
import lt.zaltauskas.android_party.R;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ViewHolder> {

    private static final String UNITS = "Km";
    private List<Server> items = new ArrayList<>();

    public ServerAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Server item = getItem(position);
        holder.name.setText(item.getName());
        holder.distance.setText(String.format("%s %s", item.getDistance(), UNITS));
    }

    @NonNull
    @Override
    public ServerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.item_server, parent, false);
        return new ViewHolder(contactView);
    }

    public void setItems(List<Server> serverList) {
        items.addAll(serverList);
    }

    private Server getItem(int position) {
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView distance;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.item_server_name);
            distance = v.findViewById(R.id.item_server_distance);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
