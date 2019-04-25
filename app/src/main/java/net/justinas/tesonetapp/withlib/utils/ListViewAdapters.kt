package net.justinas.tesonetapp.withlib.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.util.LoadResult
import net.justinas.tesonetapp.withlib.view.adapter.IdLinearEntityAdapter
import retrofit2.HttpException
import timber.log.Timber

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
    fun SwipeRefreshLayout.hideLoad(@Suppress("UNUSED_PARAMETER") result: LoadResult<*>?) {
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

    @JvmStatic
    @BindingAdapter("showCustomErrorSnack")
    fun View.showSnackbar(result: LoadResult<*>?) {
        (result as? LoadResult.Error)?.exception?.let {
            Timber.e(it)
            when (it.cause) {
                is HttpException -> Snackbar.make(
                    this,
                    "Error " + (it.cause as HttpException).message(),
                    LENGTH_LONG
                ).show()
                else -> Snackbar.make(this, "Error " + result.exception, LENGTH_LONG).show()
            }
        }
    }
}