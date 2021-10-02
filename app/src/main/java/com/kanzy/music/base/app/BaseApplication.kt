package com.kanzy.music.base.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.kanzy.music.BuildConfig
import timber.log.Timber

open class BaseApplication : Application(), LifecycleObserver {

    var isAppInForeground: Boolean = true

    open fun timberLogsEnable(): Boolean = true

    /**
     * @sample Enable for configureCrashLogs(...) method
     */
    open fun crashLogsEnable(): Boolean = false

    /**
     * @param throwable, you can integrate firebase crash log here, if crashLogsEnable is true
     * todo: google + huawei Firebase or Huawei Crash Reporting
     */
    open fun configureCrashLogs(throwable: Throwable, tag: String?, message: String) {}

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerActivityLifecycleCallbacks(ActivityLifecycleCallback())
        if (timberLogsEnable()) {
            configureTimberLog()
        }
    }

    private fun configureTimberLog() {
        val crashReportingTree = CrashReportingTree()
        crashReportingTree.crashReporting = { throwable, tag, message ->
            if (crashLogsEnable()) {
                configureCrashLogs(throwable, tag, message)
            }
        }
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else crashReportingTree)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        isAppInForeground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        //App in foreground
        isAppInForeground = true
    }

}