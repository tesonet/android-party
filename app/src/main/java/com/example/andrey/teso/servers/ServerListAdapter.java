package com.example.andrey.teso.servers;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.andrey.teso.R;

import java.util.ArrayList;

public class ServerListAdapter extends ArrayAdapter<Server> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView idLocation;
        TextView distance;
    }

    public ServerListAdapter(Context context, int resource, ArrayList<Server> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer id = getItem(position).getIdentifier();
        String location = getItem(position).getLocation();
        Integer distance = getItem(position).getDistance();

        Server server = new Server(id, location, distance);

        final View result;
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.idLocation = convertView.findViewById(R.id.textView1);
            holder.distance = convertView.findViewById(R.id.textView2);

            result = convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.load_down : R.anim.load_up);
        result.startAnimation(animation);
        lastPosition = position;

        holder.idLocation.setText(server.getLocation() + " #" + server.getIdentifier());
        holder.distance.setText(server.getDistance() + " km");

        return convertView;
    }
}