package com.kanzy.music.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.observeState(property: Flow<T>, block: (T) -> Unit) {
    lifecycleScope.launch {
        property.collect { block(it) }
    }
}

fun <T> LifecycleOwner.observeLatestState(property: Flow<T>, block: (T) -> Unit) {
    lifecycleScope.launch {
        property.collectLatest { block(it) }
    }
}