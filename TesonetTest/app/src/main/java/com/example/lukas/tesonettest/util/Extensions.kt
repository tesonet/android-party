package com.example.lukas.tesonettest.util

import android.content.res.Resources

/**
 * Created by lukas on 17.8.17.
 */
val Int.dp: Int
	get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Float
	get() = (this * Resources.getSystem().displayMetrics.density)
