package com.kanzy.music.helper

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes id: Int): String
}