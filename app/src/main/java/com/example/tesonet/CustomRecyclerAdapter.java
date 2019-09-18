package com.example.tesonet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;



    public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

        private Context context;
        List<Server> servers;

        public CustomRecyclerAdapter(Context context, List servers) {
            this.context = context;
            this.servers = servers;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_listitem, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }





        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setTag(servers.get(position));

            Server server = servers.get(position);

            holder.location.setText(server.getName());
            holder.distance.setText(String.valueOf(server.getDistance()) + " km");

        }

        @Override
        public int getItemCount() {
            return servers.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView distance;
            public TextView location;

            public ViewHolder(View itemView) {
                super(itemView);

                location = (TextView) itemView.findViewById(R.id.locationTextView);
                distance = (TextView) itemView.findViewById(R.id.distanceTextView);

            }
        }


    }

