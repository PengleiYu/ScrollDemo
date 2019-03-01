package com.example.scrolldemo

import android.content.Context
import android.support.v4.view.NestedScrollingChild2
import android.support.v4.view.NestedScrollingChildHelper
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

/**
 * @author penglei on 2019/3/1
 */
class ScrollerChild : ImageView, NestedScrollingChild2 {
    companion object {
        private val TAG = ScrollerChild::class.java.simpleName
    }

    private val helper = NestedScrollingChildHelper(this)

    /**
     * 是否有可滚动的父View
     */
    override fun hasNestedScrollingParent(type: Int): Boolean {
        return helper.hasNestedScrollingParent(type)
    }

    override fun hasNestedScrollingParent(): Boolean {
        return helper.hasNestedScrollingParent()
    }

    /**
     * 设置是否可滚动
     */
    override fun setNestedScrollingEnabled(enabled: Boolean) {
        helper.isNestedScrollingEnabled = enabled
    }

    /**
     * 是否可滚动
     */
    override fun isNestedScrollingEnabled(): Boolean {
        return helper.isNestedScrollingEnabled
    }

    /**
     * 本View开始滚动
     */
    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        return helper.startNestedScroll(axes, type)
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return helper.startNestedScroll(axes)
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
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
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
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type)
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    /**
     * 本View停止滚动
     */
    override fun stopNestedScroll(type: Int) {
        return helper.stopNestedScroll(type)
    }

    override fun stopNestedScroll() {
        return helper.stopNestedScroll()
    }

    /**
     * 滑行相关
     */
    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return helper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return helper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    // ===================
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setWillNotDraw(false)
    }

    private val mConsumed = IntArray(2)
    private val mOffset = IntArray(2)
    private var mLastY = -1;

    override fun onTouchEvent(event: MotionEvent): Boolean {
        log("onTouchEvent: action=${event.action}")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                mLastY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = 0
                val dy = 100
                val deltaX = 0
                val deltaY = mLastY - event.y.toInt()
                log("scrollY=$scrollY")
                getLocationInWindow(mOffset)
                dispatchNestedPreScroll(dx, dy, mConsumed, mOffset)
                scrollBy(deltaX, deltaY)
                dispatchNestedScroll(
                    mConsumed[0], mConsumed[1],
                    dx - mConsumed[0], dy - mConsumed[1], mOffset
                )
                mLastY = event.y.toInt()
            }
            MotionEvent.ACTION_UP -> {
                stopNestedScroll()
            }
        }
//        return super.onTouchEvent(event)
        return true
    }

    private fun log(msg: String) {
        Log.d(">>> $TAG", msg)
    }
}