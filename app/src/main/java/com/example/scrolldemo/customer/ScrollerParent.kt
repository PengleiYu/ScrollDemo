package com.example.scrolldemo.customer

import android.content.Context
import android.support.v4.view.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout

/**
 * @author penglei on 2019/3/1
 */
class ScrollerParent : FrameLayout, NestedScrollingParent2, NestedScrollingChild2 {
    companion object {
        private val TAG = ScrollerParent::class.java.simpleName
    }

    private val parentHelper = NestedScrollingParentHelper(this)
    private val childHelper = NestedScrollingChildHelper(this)

    // ==============================Parent==============================

    /**
     * @return true表示允许子View嵌套滚动
     */
    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        // 允许滑动
        return true
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        return onStartNestedScroll(child, target, axes, ViewCompat.TYPE_TOUCH)
    }

    /**
     * 在子View进行滚动前的资源初始化
     * [onStartNestedScroll]返回true，后调用
     */
    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        return parentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        return parentHelper.onNestedScrollAccepted(child, target, axes)
    }

    /**
     * 其值由[onNestedScrollAccepted]设置
     */
    override fun getNestedScrollAxes(): Int {
        return parentHelper.nestedScrollAxes
    }

    /**
     * 在子View滚动前，可能本View需要滚动或其他操作
     */
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray?, type: Int) {
        // 不进行预消耗
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        onNestedPreScroll(target, dx, dy, consumed, ViewCompat.TYPE_TOUCH)
    }

    /**
     * 滚动过后，分析子View消耗和未消耗的距离，进行某些操作
     */
    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        // do nothing
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, ViewCompat.TYPE_TOUCH)
    }

    /**
     * 子View滚动停止时
     */
    override fun onStopNestedScroll(target: View, type: Int) {
        parentHelper.onStopNestedScroll(target, type)
    }

    override fun onStopNestedScroll(target: View) {
        parentHelper.onStopNestedScroll(target)
    }

    /**
     * 滑行相关
     */
    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        // 允许滑行
        return true
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        // 不拦截滑动
        return false
    }

    // ========================Child========================

    /**
     * 是否有可滚动的父View
     */
    override fun hasNestedScrollingParent(type: Int): Boolean {
        return childHelper.hasNestedScrollingParent(type)
    }

    override fun hasNestedScrollingParent(): Boolean {
        return childHelper.hasNestedScrollingParent()
    }

    /**
     * 设置是否可滚动
     */
    override fun setNestedScrollingEnabled(enabled: Boolean) {
        childHelper.isNestedScrollingEnabled = enabled
    }

    /**
     * 是否可滚动
     */
    override fun isNestedScrollingEnabled(): Boolean {
        return childHelper.isNestedScrollingEnabled
    }

    /**
     * 本View分发预滚动
     */
    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    /**
     * 本View开始滚动
     */
    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        return childHelper.startNestedScroll(axes, type)
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return childHelper.startNestedScroll(axes)
    }

    /**
     * 本View分发已滚动
     */
    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return childHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type
        )
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    /**
     * 本View停止滚动
     */
    override fun stopNestedScroll(type: Int) {
        return childHelper.stopNestedScroll(type)
    }

    override fun stopNestedScroll() {
        return childHelper.stopNestedScroll()
    }

    /**
     * 滑行相关
     */
    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }
    // ============================

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        measuredWidth
//    }
//
//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        if (childCount == 0) return
//        getChildAt(0).let {
//            it.layout(l, t, r, t + it.measuredHeight)
//            log("it height= ${it.measuredHeight}")
//        }
//    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun log(msg: String) {
        Log.d(">>> $TAG", msg)
    }
}