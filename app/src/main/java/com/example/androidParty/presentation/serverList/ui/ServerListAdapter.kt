package com.example.androidParty.presentation.serverList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidParty.databinding.FragmentServerItemBinding
import com.example.androidParty.presentation.serverList.domain.entity.Server

class ServerListAdapter : ListAdapter<Server, ServerListAdapter.TaskViewHolder>(DiffCallback()) {
    class TaskViewHolder(private val bind: FragmentServerItemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(server: Server) {
            bind.serverData = server
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            FragmentServerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Server>() {
        override fun areItemsTheSame(oldItem: Server, newItem: Server): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Server, newItem: Server): Boolean {
            return oldItem.name == newItem.name

        }
    }
}