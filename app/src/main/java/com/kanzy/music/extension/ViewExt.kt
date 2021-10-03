package com.kanzy.music.extension

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.onFocusChanged(func: (Boolean) -> Unit) {
    this.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus -> func.invoke(hasFocus) }
}

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun View.setSafeOnClickListener(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}