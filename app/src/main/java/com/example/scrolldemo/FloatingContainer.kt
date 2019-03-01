package com.example.scrolldemo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.example.scrolldemo.customer.ScrollerParent

/**
 * @author penglei on 2019/3/1
 */
class FloatingContainer : ScrollerParent {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
//        log("dyConsumed=$dyConsumed, dyUnconsumed=$dyUnconsumed")
        val img = img ?: return
        (img.layoutParams as MarginLayoutParams).topMargin += dyConsumed
        img.requestLayout()
        log("top=${img.top}, left=${img.left}, right=${img.right}, bottom=${img.bottom}")
    }

    var img: ImageView? = null
}