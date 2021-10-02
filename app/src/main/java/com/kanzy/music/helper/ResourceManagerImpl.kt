package com.kanzy.music.helper

import android.content.Context
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(private val context: Context) : ResourceManager {

    override fun getString(id: Int): String {
        return context.getString(id)
    }

}