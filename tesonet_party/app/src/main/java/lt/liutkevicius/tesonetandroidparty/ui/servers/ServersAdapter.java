package lt.liutkevicius.tesonetandroidparty.ui.servers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lt.liutkevicius.tesonetandroidparty.R;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ViewHolder> {
    //private List<Server> serverList;

    @NonNull
    @Override
    public ServersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servers_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView server;
        public TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);
            server = itemView.findViewById(R.id.server_name);
            distance = itemView.findViewById(R.id.server_distance);
        }
    }
}
