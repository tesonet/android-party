package lajevski.radoslav.tesonetparty.ui.fragments.servers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.ui.base.BaseViewHolder;
import lajevski.radoslav.tesonetparty.utils.AppString;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class ServerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Server> mServersList;

    public ServerAdapter(List<Server> serversList) {
        mServersList = serversList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.server_item, parent, false));

    }


    @Override
    public int getItemCount() {
        if (mServersList != null && mServersList.size() > 0) {
            return mServersList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Server> serversList) {
        mServersList.addAll(serversList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.li_server_tv_name)
        TextView mTVServerName;

        @BindView(R.id.li_server_tv_distance)
        TextView mTVServerDistance;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void onBind(int position) {
            super.onBind(position);

            Server server = mServersList.get(position);

            if (!AppString.isEmpty(server.getName())) {
                mTVServerName.setText(server.getName());
            }

            if (server.getDistance() != null) {
                mTVServerDistance.setText(server.getStringDistance());
            }

        }
    }

}
