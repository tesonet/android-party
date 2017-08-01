package com.yegor.tesonet.partyapp.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yegor.tesonet.partyapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View holder for {@link ServersListAdapter}
 */
class ServerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.distance)
    TextView distance;

    ServerViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
