package com.kanzy.music.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(this) { block(it) }
}

fun <T : Any, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, { t -> body(t) })
}