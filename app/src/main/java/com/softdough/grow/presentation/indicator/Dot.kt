package com.softdough.grow.presentation.indicator

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.softdough.grow.R

class Dot(context: Context, highlighting: Boolean = false, custom: Boolean) :
    View(context) {

    val dotDefaultResource: Int = R.drawable.ic_indicator_default
    val dotHighlightResource: Int = R.drawable.ic_indicator_highlight

    val dotCustomDefaultResource: Int = R.drawable.ic_indicator_blue_default
    val dotCustomHighlightResource: Int = R.drawable.ic_indicator_blue_highlight

    val dotNormal: Drawable? by lazy {
        when (custom) {
            true -> ContextCompat.getDrawable(context, dotCustomDefaultResource)
            false -> ContextCompat.getDrawable(context, dotDefaultResource)
        }
    }
    val dotHighLight: Drawable? by lazy {
        when (custom) {
            true -> ContextCompat.getDrawable(context, dotCustomHighlightResource)
            false -> ContextCompat.getDrawable(context, dotHighlightResource)
        }
    }

    init {
        setState(highlighting)
    }

    fun setState(highlighting: Boolean = false) {
        if (highlighting)
            setBackground(dotHighLight)
        else
            setBackground(dotNormal)
    }
}