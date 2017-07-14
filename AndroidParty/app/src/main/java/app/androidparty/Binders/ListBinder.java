package app.androidparty.Binders;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ListView;

import app.androidparty.Adapters.ListAdapter;
import app.androidparty.Models.Server;

public class ListBinder  {

    @BindingAdapter("bind:items")
    public static void bindList(ListView view, ObservableArrayList<Server> list) {
        ListAdapter adapter = new ListAdapter(list);
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}