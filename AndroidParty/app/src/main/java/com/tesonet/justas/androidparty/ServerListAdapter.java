package com.tesonet.justas.androidparty;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ServerListAdapter extends BaseAdapter {
    private final Context mContext;
    private List<Pair<String, String>> mServerList;
    public ServerListAdapter(Context context, List<Pair<String, String>> serverList) {
        mContext = context;
        mServerList = serverList;
    }

    static class ViewHolder {
        public TextView country;
        public TextView distance;
    }

    @Override
    public int getCount() {
        return mServerList.size();
    }

    @Override
    public Object getItem(int position) {
        return mServerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mServerList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.country = convertView.findViewById(R.id.list_item_country);
            holder.distance = convertView.findViewById(R.id.list_item_distance);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Pair<String, String> item = mServerList.get(position);

        holder.country.setText(item.first);
        String distanceInKm = item.second + " km";
        holder.distance.setText(distanceInKm);

        return convertView;
    }
}
