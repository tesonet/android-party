@file:Suppress("DEPRECATION")

package core.ui.elements

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import core.ui.state.Loading
import core.ui.state.placeholder
import core.ui.state.placeholderHighlight

private fun Modifier.placeholder(visible: Boolean, highlight: Boolean, shape: Shape? = null) =
    this.composed {
        placeholder(
            visible,
            shape = shape,
            highlight = if (highlight) PlaceholderHighlight.fade() else null
        )
    }

fun Modifier.placeholder(loading: Loading?, shape: Shape? = null) =
    placeholder(loading.placeholder, loading.placeholderHighlight, shape)