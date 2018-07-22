package com.amaximan.tesonet.other.adapters.rv

import android.support.v7.util.DiffUtil
import com.amaximan.tesonet.model.database.base.BaseEntity

internal class PostDiffCallback(private val oldPosts: List<BaseEntity>, private val newPosts: List<BaseEntity>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldPosts.size
    }

    override fun getNewListSize(): Int {
        return newPosts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition].id == newPosts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] == newPosts[newItemPosition]
    }
}