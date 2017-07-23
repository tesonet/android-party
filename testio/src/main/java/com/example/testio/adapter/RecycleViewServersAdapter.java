package com.example.testio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.testio.R;
import com.example.testio.models.Server;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mantas on 7/23/17.
 */

public class RecycleViewServersAdapter
    extends RecyclerView.Adapter<RecycleViewServersAdapter.MyViewHolder> {

  private List<Server> serverList;

  public RecycleViewServersAdapter() {
    //this.serverList = serverList;
    this.serverList = new ArrayList<>();
  }

  public void changeData(List<Server> serverList) {
    this.serverList = serverList;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servers, parent, false);

    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    Server server = serverList.get(position);
    String distance = server.getDistance() + " km";
    holder.serverName.setText(server.getName());
    holder.serverDistance.setText(distance);
  }

  @Override
  public int getItemCount() {
    return serverList.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView serverName, serverDistance;

    public MyViewHolder(View view) {
      super(view);
      serverName = (TextView) view.findViewById(R.id.textView_server);
      serverDistance = (TextView) view.findViewById(R.id.textView_distance);
    }
  }
}

