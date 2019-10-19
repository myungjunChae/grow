package com.softdough.grow.util

import android.content.res.Resources
import android.util.DisplayMetrics

object ScreenUtil {
    private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

    fun deviceWidth() : Int = displayMetrics.widthPixels

    fun deviceHeight() : Int = displayMetrics.heightPixels

    fun dpToPx(dp: Int): Float = (dp * displayMetrics.density)

    fun pxToDp(px: Int): Float = (px / displayMetrics.density)

    fun percentToPxWidth(percent: Float): Float = dpToPx((displayMetrics.widthPixels / displayMetrics.density).toInt()) * percent

    fun percentToPxHeight(percent: Float): Float = (displayMetrics.heightPixels / displayMetrics.density) * percent
}