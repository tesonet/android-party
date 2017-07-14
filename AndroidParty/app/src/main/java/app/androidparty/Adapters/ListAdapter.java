package app.androidparty.Adapters;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.androidparty.Models.Server;
import app.androidparty.R;

public class ListAdapter extends BaseAdapter{


    static class ViewHolder {
        TextView name;
        TextView distance;
    }

    private ObservableArrayList<Server> list;
    private LayoutInflater inflater;

    public ListAdapter(ObservableArrayList<Server> l) {
        list = l;
    }

    @Override
    public int getCount() {
        if(list!=null){

            return list.size();
        }
        else{
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            inflater = (LayoutInflater) viewGroup.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.server_row, viewGroup, false);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.name);
            holder.distance = (TextView)view.findViewById(R.id.distance);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        Server item = list.get(i);
        holder.name.setText(item.getName());
        holder.distance.setText(item.getDistance().toString() + " km");

        return view;
    }
}
