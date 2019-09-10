package com.jonastiskus.testio.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.ui.adapter.ServerListAdapter

class ServerListRecyclerView : RecyclerView {


    private var serverAdapter: ServerListAdapter = ServerListAdapter()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        adapter = serverAdapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun setData(data: List<ServerGsonModel>) {
        serverAdapter.setData(data)
    }
}