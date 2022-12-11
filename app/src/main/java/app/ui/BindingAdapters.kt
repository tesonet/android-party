package app.ui

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("bind:errorText")
    fun errorText(view: TextInputLayout, errorText: String?) {
        view.error = errorText
    }

    @JvmStatic
    @BindingAdapter("bind:refreshing")
    fun refreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
        view.isRefreshing = refreshing
    }

    @JvmStatic
    @BindingAdapter("bind:enabled")
    fun enabled(view: SwipeRefreshLayout, enabled: Boolean) {
        view.isEnabled = enabled
    }
}