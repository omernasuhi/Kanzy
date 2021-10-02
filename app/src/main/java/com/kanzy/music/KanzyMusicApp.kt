package com.kanzy.music

import com.kanzy.music.base.app.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KanzyMusicApp : BaseApplication() {

    companion object {
        private var instance: KanzyMusicApp? = null

        fun getContext(): KanzyMusicApp {
            return instance!!
        }
    }


    override fun timberLogsEnable(): Boolean {
        return BuildConfig.IsProd.not()
    }

    override fun crashLogsEnable(): Boolean {
        return BuildConfig.IsProd
    }

    override fun configureCrashLogs(throwable: Throwable, tag: String?, message: String) {
        super.configureCrashLogs(throwable, tag, message)
        // todo: google + huawei services
        /* Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
         Firebase.crashlytics.log(throwable.message.toString())
         Firebase.crashlytics.setCustomKey(tag.toString(), throwable.message.toString())
         Firebase.crashlytics.recordException(throwable)*/
    }


}