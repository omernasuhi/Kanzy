package com.kanzy.music.base.app

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.kanzy.music.BuildConfig
import com.kanzy.music.extension.classTag
import com.kanzy.music.extension.info

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        info(tag = activity.classTag, message = "onCreate()")
        activity.allowDebugRotation()
        activity.registerFragmentLifecycleCallbacks()
    }

    override fun onActivityStarted(activity: Activity) {
        info(tag = activity.classTag, message = "onStart()")
    }

    override fun onActivityResumed(activity: Activity) {
        info(tag = activity.classTag, message = "onResume()")
    }

    override fun onActivityPaused(activity: Activity) {
        info(tag = activity.classTag, message = "onPause()")
    }

    override fun onActivityStopped(activity: Activity) {
        info(tag = activity.classTag, message = "onStop()")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        info(tag = activity.classTag, message = "onSaveInstanceState()")
    }

    override fun onActivityDestroyed(activity: Activity) {
        info(tag = activity.classTag, message = "onDestroy()")
    }
}

private fun Activity.allowDebugRotation() {
    requestedOrientation = if (BuildConfig.DEBUG) {
        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    } else {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

fun Activity.registerFragmentLifecycleCallbacks() {
    if (this is FragmentActivity) {
        supportFragmentManager
            .registerFragmentLifecycleCallbacks(object :
                FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentViewCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    v: View,
                    savedInstanceState: Bundle?
                ) {
                    super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                    info(tag = f.classTag, message = "onCreateView()")
                }

                override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                    super.onFragmentResumed(fm, f)
                    info(tag = f.classTag, message = "onResume()")
                }

                override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                    super.onFragmentPaused(fm, f)
                    info(tag = f.classTag, message = "onPause()")
                }

                override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                    super.onFragmentViewDestroyed(fm, f)
                    info(tag = f.classTag, message = "onDestroyView()")
                }
            }, true)
    }
}