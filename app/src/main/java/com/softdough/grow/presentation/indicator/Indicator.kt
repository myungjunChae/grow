package com.softdough.grow.presentation.indicator

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class Indicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var custom: Boolean = false
    var dotList: ArrayList<Dot> = ArrayList()
    var pageCount: Int = 0
        set(value) {
            field = value
            detachDot()

            if(value == 1)
                return

            for (i in 1..value)
                attachDot(i, custom)
        }
    var currentPage: Int = 0
    var dotSize: Int = 10 * context.resources.displayMetrics.density.toInt() //dp
    var paddingSize: Int = 8 * context.resources.displayMetrics.density.toInt() //dp

    fun attachDot(index: Int, custom: Boolean) {
        var dot = Dot(context, index == 0, custom)

        dot.apply {
            this.layoutParams = LayoutParams(dotSize, dotSize).apply {
                if (!dotList.isEmpty()) {
                    setMargins(paddingSize, 0, 0, 0)
                }
            }
        }.run {
            dotList.add(this)
            addView(this)
        }
    }

    fun detachDot() {
        removeAllViews()
    }

    fun updateDotState() {
        Log.i("", "updateDotState")
        println("닷리스트 ${dotList.size}")
        dotList.forEachIndexed { index, dot ->
            if (index == currentPage)
                dot.setState(true)
            else
                dot.setState()
        }
    }
}