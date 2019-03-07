package com.example.scrolldemo

import android.content.Context
import android.support.v4.view.NestedScrollingParent2
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.scrolldemo.customer.ScrollerParent

/**
 * @author penglei on 2019/3/1
 */
class FloatingContainer : ScrollerParent, NestedScrollingParent2 {
    private val archChild by lazy { findViewById<View>(R.id.floatingChild) }
    private val scrollChild by lazy { this.findViewById<ViewGroup>(R.id.scrollChild) }

    private fun isArch(view: View): Boolean {
//        log("isArch? ${archChild == view}")
        return archChild == view
    }

//    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
//        onNestedScrollAccepted(child, target, axes)
//    }
//
//    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
//        if (isArch(child)) scrollChild.onNestedScrollAccepted(child, target, axes)
//        else super.onNestedScrollAccepted(child, target, axes)
//    }
//
//    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
//        return onStartNestedScroll(child, target, axes)
//    }
//
//    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
//        return if (isArch(child)) scrollChild.onStartNestedScroll(child, target, axes)
//        else super.onStartNestedScroll(child, target, axes)
//    }
//
//    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
//        return if (isArch(target)) scrollChild.onNestedFling(target, velocityX, velocityY, consumed)
//        else super.onNestedFling(target, velocityX, velocityY, consumed)
//    }
//
//    override fun getNestedScrollAxes(): Int {
//        return scrollChild.nestedScrollAxes
//    }
//
//    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray?, type: Int) {
//        consumed ?: return
//        onNestedPreScroll(target, dx, dy, consumed)
//    }
//
//    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
//        if (isArch(target)) scrollChild.onNestedPreScroll(target, dx, dy, consumed)
//        else super.onNestedPreScroll(target, dx, dy, consumed)
//    }
//
//    override fun onNestedScroll(
//        target: View,
//        dxConsumed: Int,
//        dyConsumed: Int,
//        dxUnconsumed: Int,
//        dyUnconsumed: Int,
//        type: Int
//    ) {
//        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
//    }
//
//    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
//        if (isArch(target)) scrollChild.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
//        else super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
//    }
//
//    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
//        return if (isArch(target)) scrollChild.onNestedPreFling(target, velocityX, velocityY)
//        else super.onNestedPreFling(target, velocityX, velocityY)
//    }
//
//    override fun onStopNestedScroll(target: View, type: Int) {
//        onStopNestedScroll(target)
//    }
//
//    override fun onStopNestedScroll(target: View) {
//        if (isArch(target)) scrollChild.onStopNestedScroll(target)
//        else super.onStopNestedScroll(target)
//    }

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
        val b = this == (target.parent as ViewGroup)
        if (!b) return
        val img = archChild ?: return
//        (img.layoutParams as MarginLayoutParams).topMargin += dyConsumed
//        img.requestLayout()
        img.offsetTopAndBottom(-dyConsumed)
//        log("top=${img.top}, left=${img.left}, right=${img.right}, bottom=${img.bottom}")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        log("dispatchTouchEvent: action=${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        log("onTouchEvent: action=${event?.action}")
        return super.onTouchEvent(event)
    }

    override fun computeScroll() {
        log("computeScroll")
        super.computeScroll()
    }

//    private var cacheEvent: MotionEvent? = null
//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
////        when (ev.action) {
////            MotionEvent.ACTION_DOWN -> {
////                cacheEvent = ev
////                return false
////            }
//////            MotionEvent.ACTION_MOVE -> {
////            else -> {
////                cacheEvent?.let {
////                    super.dispatchTouchEvent(it)
////                    cacheEvent = null
////                }
////                return super.dispatchTouchEvent(ev)
////            }
////        }
//        return super.dispatchTouchEvent(ev)
//    }


}