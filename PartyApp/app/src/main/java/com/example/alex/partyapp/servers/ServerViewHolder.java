package com.example.alex.partyapp.servers;

import android.support.v7.widget.RecyclerView;

import com.example.alex.partyapp.R;
import com.example.alex.partyapp.data.Server;
import com.example.alex.partyapp.databinding.ServerItemBinding;


public class ServerViewHolder extends RecyclerView.ViewHolder  {

    public ServerItemBinding binding;
    public ServerViewHolder(ServerItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Server server) {
        binding.textName.setText(server.getName());
        binding.textDistance.setText(String.format("%s %s", server.getDistance(), binding.getRoot().getContext().getString(R.string.km)));
    }
}
