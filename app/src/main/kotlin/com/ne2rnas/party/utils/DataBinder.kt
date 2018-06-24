@file:Suppress("unused")

package com.ne2rnas.party.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.InsetDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.ne2rnas.party.R
import com.ne2rnas.party.ui.servers.ServersAdapter


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: ServersAdapter) {
    view.adapter = adapter
}

@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    val attrs = intArrayOf(android.R.attr.listDivider)
    val a = view.context.obtainStyledAttributes(attrs)
    val inset = view.resources.getDimensionPixelSize(R.dimen.servers_header_side_margin)
    val insetDivider = InsetDrawable(ContextCompat.getDrawable(view.context,
            R.drawable.server_item_divider)!!,
            inset,
            0,
            inset,
            0)
    a.recycle()
    dividerItemDecoration.setDrawable(insetDivider)
    view.addItemDecoration(dividerItemDecoration)
}
