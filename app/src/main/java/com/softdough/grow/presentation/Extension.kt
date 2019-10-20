package com.softdough.grow.presentation

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Activity.bindColor(@ColorRes res: Int): Lazy<Int> = lazy {
    ContextCompat.getColor(this, res)
}