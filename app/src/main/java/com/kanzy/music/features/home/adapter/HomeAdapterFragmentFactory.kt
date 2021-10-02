package com.kanzy.music.features.home.adapter

import androidx.fragment.app.Fragment

interface HomeAdapterFragmentFactory {

    fun createSearchFragment(): Fragment

    fun createDiscoveryFragment(): Fragment

    fun createLibraryFragment(): Fragment

}