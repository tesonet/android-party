/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.ui.util

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Extension method for the View to create a snackbar with [messageRes] string resource,
 * [length] duration, execute [f] and show it. Snackbar text is multiline.
 */
inline fun View.showSnackbar(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_INDEFINITE,
    f: Snackbar.() -> Unit
): Snackbar {
    return showSnackbar(resources.getString(messageRes), length, f)
}

/**
 * Extension method for the View to create a snackbar with [message] string, [length] duration,
 * execute [f] and show it. Snackbar text is multiline.
 */
inline fun View.showSnackbar(
    message: String, length: Int = Snackbar.LENGTH_INDEFINITE,
    f: Snackbar.() -> Unit
): Snackbar {

    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 5
    snack.show()
    return snack
}

/**
 * Extension method for the Snackbar to set action with [textRes] string resource, [color] color
 * and [listener] action lambda.
 */
fun Snackbar.onActionButtonClick(
    @StringRes textRes: Int, color: Int? = null,
    listener: (View) -> Unit
) {
    onActionButtonClick(view.resources.getString(textRes), color, listener)
}

/**
 * Extension method for the Snackbar to set action with [text] string, [color] color
 * and [listener] action lambda.
 */
fun Snackbar.onActionButtonClick(text: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(text, listener)
    color?.let { setActionTextColor(color) }
}