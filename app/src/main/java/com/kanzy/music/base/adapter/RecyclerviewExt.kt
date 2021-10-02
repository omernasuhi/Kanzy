package com.kanzy.music.base.adapter

import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setSnapHelper(addSnapHelper: Boolean) {
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)
}

fun RecyclerView.setDefaultLayoutManager() {
    if (layoutManager != null) return
    layoutManager = LinearLayoutManager(context)
}

fun RecyclerView.getOrientation(): Int? {
    return if (layoutManager is LinearLayoutManager) {
        val layoutManager = layoutManager as LinearLayoutManager
        layoutManager.orientation
    } else
        null
}

fun RecyclerView.addDividerDrawable(dividerDrawable: Drawable) {
    getOrientation()?.let { orientation ->
        val dividerItemDecoration = DividerItemDecorator(dividerDrawable, orientation)
        addItemDecoration(dividerItemDecoration)
    }
}

private val timeAnimator = TimeAnimator().apply {
    duration = 1
    this.repeatMode = ValueAnimator.RESTART
}

@SuppressLint("ClickableViewAccessibility")
fun RecyclerView.endlessScrollHorizontally(scroll: Boolean) {
    if (!scroll)
        return

    layoutManager?.scrollToPosition(Integer.MAX_VALUE / 2);
    timeAnimator.removeAllListeners()
    timeAnimator.pause()
    timeAnimator.cancel()

    timeAnimator.setTimeListener { animator, p1, p2 ->
        this.scrollBy(1, 0)
    }
    timeAnimator.start()

    this.setOnTouchListener { view, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            timeAnimator.pause()
            timeAnimator.cancel()
        }
        if (motionEvent.action == MotionEvent.ACTION_UP) {
            timeAnimator.start()
        }
        return@setOnTouchListener false
    }
}