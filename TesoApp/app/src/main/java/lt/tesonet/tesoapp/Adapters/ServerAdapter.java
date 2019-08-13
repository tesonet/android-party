package lt.tesonet.tesoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lt.tesonet.tesoapp.Models.Server;
import lt.tesonet.tesoapp.R;

public class ServerAdapter extends ArrayAdapter<Server> {

    public ServerAdapter(Context context, int resource, Server[] items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_server, null);
        }

        Server p = getItem(position);

        if (p != null) {
            TextView textName=v.findViewById(R.id.txtServerName);
            TextView textDist=v.findViewById(R.id.txtServerDistance);

            textName.setText(p.name);
            textDist.setText(p.distance+" km");
        }

        return v;
    }
}
