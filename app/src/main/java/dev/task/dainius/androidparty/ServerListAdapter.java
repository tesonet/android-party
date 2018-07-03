package dev.task.dainius.androidparty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServerListAdapter extends ArrayAdapter<Server> {

    public ServerListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ServerListAdapter(Context context, int resource, List<Server> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.server_list_row, null);
        }

        Server s = getItem(position);

        if (s != null ){
            TextView txtName = v.findViewById(R.id.text_server_name);
            TextView txtDistance = v.findViewById(R.id.text_server_distance);

            txtName.setText(s.getName());
            txtDistance.setText(String.valueOf(s.getDistance()));
        }

        return v;
    }
}
