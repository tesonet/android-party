package com.example.featureServer.presentation.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.featureServer.databinding.ItemServerBinding
import com.example.featureServer.presentation.model.ServerUiModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ServerListAdapter @Inject constructor() :
    AsyncListDifferDelegationAdapter<ServerUiModel>(ItemDifferCallback()) {
    init {
        with(delegatesManager) {
            this += itemServerDelegate()
        }
    }
}

operator fun <T> AdapterDelegatesManager<T>.plusAssign(adapterDelegate: AdapterDelegate<T>?) {
    if (adapterDelegate != null) {
        addDelegate(adapterDelegate)
    }
}

internal fun itemServerDelegate() =
    adapterDelegateViewBinding<ServerUiModel, ServerUiModel, ItemServerBinding>(
        { layoutInflater, parent -> ItemServerBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            with(binding) {
                name.text = item.serverName
                distance.text = item.distance
            }
        }
    }

class ItemDifferCallback : DiffUtil.ItemCallback<ServerUiModel>() {
    override fun areItemsTheSame(oldItem: ServerUiModel, newItem: ServerUiModel): Boolean {
        return oldItem.serverName == newItem.serverName
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ServerUiModel, newItem: ServerUiModel): Boolean {
        return oldItem == newItem
    }
}
