package lt.liutkevicius.tesonetandroidparty.ui.servers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.network.model.Server;

import java.util.List;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ViewHolder> {
    private static final String TAG = "ServersAdapter";
    private List<Server> serverList;

    ServersAdapter(List<Server> serverList) {
        this.serverList = serverList;
    }

    @NonNull
    @Override
    public ServersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servers_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServersAdapter.ViewHolder holder, int position) {
        holder.name.setText(serverList.get(position).getName());
        holder.distance.setText(String.valueOf(serverList.get(position).getDistance()));
    }

    @Override
    public int getItemCount() {
        return serverList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.server_name)
        AppCompatTextView name;
        @BindView(R.id.server_distance)
        public AppCompatTextView distance;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
