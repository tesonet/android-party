package net.justinas.tesonetapp.withlib.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.util.LoadResult
import net.justinas.tesonetapp.withlib.view.adapter.IdLinearEntityAdapter

object ListViewAdapters {
    @JvmStatic
    @BindingAdapter("listItemAdapter", "idEntityCallback", requireAll = false)
    fun RecyclerView.setReviewAdapter(
        result: LoadResult<List<IdEntity>>?,
        callback: IdLinearEntityAdapter.Callbacks
    ) {
        val productAdapter = IdLinearEntityAdapter(callback)
        adapter = productAdapter

        if (result is LoadResult.Success) {
            productAdapter.items = result.data
        }
    }

    @JvmStatic
    @BindingAdapter("hideLoad")
    fun SwipeRefreshLayout.hideLoad(result: LoadResult<*>?) {
        isRefreshing = false
    }

    @JvmStatic
    @BindingAdapter("isNotLoading")
    fun View.isNotLoading(result: LoadResult<*>?) {
        visibility = View.VISIBLE
        if (result == LoadResult.Loading) {
            visibility = View.INVISIBLE
        }
    }
}