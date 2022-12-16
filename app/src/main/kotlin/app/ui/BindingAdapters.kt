package app.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputLayout
import main.domain.model.Server
import main.ui.ServersAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("errorText")
    fun errorText(view: TextInputLayout, errorText: String?) {
        view.error = errorText
    }

    @JvmStatic
    @BindingAdapter("refreshing")
    fun refreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
        view.isRefreshing = refreshing
    }

    @JvmStatic
    @BindingAdapter("enabled")
    fun enabled(view: SwipeRefreshLayout, enabled: Boolean) {
        view.isEnabled = enabled
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun adapter(view: RecyclerView, servers: List<Server>?) {
        servers?.let { view.adapter = ServersAdapter(it) }
    }
}