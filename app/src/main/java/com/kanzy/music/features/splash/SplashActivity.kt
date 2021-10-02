package com.kanzy.music.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.kanzy.music.R
import com.kanzy.music.extension.launchActivity
import com.kanzy.music.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

// todo: Android 12 için Splash farklı olacak, şu an acelesi yok :)
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /**
         * 1,5 sn sonra MainActivity'i aç.
         */
        lifecycleScope.launchWhenCreated {
            delay(1500)
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        launchActivity<MainActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}