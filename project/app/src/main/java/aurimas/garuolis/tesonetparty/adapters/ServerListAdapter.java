package aurimas.garuolis.tesonetparty.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import aurimas.garuolis.tesonetparty.R;
import aurimas.garuolis.tesonetparty.data.PartyContract;

/**
 * Created by Aurimas on 2018.02.06.
 */

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ViewHolder> {
    Cursor mCursor;

    public void reloadCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_server, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        String serverName   = mCursor.getString(mCursor.getColumnIndex(PartyContract.ServerEntry.COL_NAME));
        float distance      = mCursor.getFloat(mCursor.getColumnIndex(PartyContract.ServerEntry.COL_DISTANCE));

        holder.mServerLabel.setText(serverName);
        holder.mDistanceLabel.setText(distance + " km");
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mServerLabel;
        public TextView mDistanceLabel;

        public ViewHolder(View v) {
            super(v);
            mServerLabel    = v.findViewById(R.id.tv_server_name);
            mDistanceLabel  = v.findViewById(R.id.tv_distance);
        }
    }

}
