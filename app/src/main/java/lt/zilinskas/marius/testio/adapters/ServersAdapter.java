package lt.zilinskas.marius.testio.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;
import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.adapters.ServersAdapter.CustomViewHolder;
import lt.zilinskas.marius.testio.api.entities.Server;

public class ServersAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    @Setter
    private List<Server> servers;
    private CompositeDisposable compositeDisposable;

    public ServersAdapter(List<Server> servers) {
        this.servers = servers;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_server, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        Server server = servers.get(i);
        customViewHolder.getName().setText(server.getName());
        customViewHolder.getDistance().setText(String.valueOf(server.getDistance()));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        compositeDisposable = new CompositeDisposable();
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return servers.size();
    }


    @Getter
    class CustomViewHolder extends RecyclerView.ViewHolder {
        private View parent;
        private TextView name;
        private TextView distance;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.parent = itemView;
            initViews();
        }

        private void initViews() {
            name = parent.findViewById(R.id.nameTextView);
            distance = parent.findViewById(R.id.distanceTextView);
        }
    }
}
