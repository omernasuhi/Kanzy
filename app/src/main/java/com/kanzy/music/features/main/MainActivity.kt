package com.kanzy.music.features.main

import android.os.Bundle
import com.kanzy.music.base.BaseActivity
import com.kanzy.music.databinding.ActivityMainBinding
import com.kanzy.music.features.home.HomeFragment
import com.kanzy.music.navigation.navigateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun createBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
        navigateFragment(HomeFragment.newInstance(), clearBackStack = true)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

}