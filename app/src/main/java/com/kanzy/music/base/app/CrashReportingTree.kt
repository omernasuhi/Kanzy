package com.kanzy.music.base.app

import android.util.Log
import timber.log.Timber

internal class CrashReportingTree : Timber.Tree() {

    var crashReporting: (Throwable, String?, String) -> Unit = { _, _, _ -> }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        val throwable = t ?: Exception(message)
        crashReporting.invoke(throwable, tag, message)
    }
}