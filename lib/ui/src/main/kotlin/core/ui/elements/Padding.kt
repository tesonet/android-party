package core.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalLayoutDirection

fun Modifier.paddingNoBottom(paddingValues: PaddingValues) =
    this.composed {
        val direction = LocalLayoutDirection.current
        padding(
            start = paddingValues.calculateStartPadding(direction),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(direction)
        )
    }