package com.kanzy.music.features.home.adapter

import androidx.fragment.app.Fragment
import com.kanzy.music.features.discovery.DiscoveryFragment
import com.kanzy.music.features.library.LibraryFragment
import com.kanzy.music.features.search.SearchFragment

class HomeAdapterFragmentFactoryImpl : HomeAdapterFragmentFactory {
    override fun createSearchFragment(): Fragment {
        return SearchFragment.newInstance()
    }

    override fun createDiscoveryFragment(): Fragment {
        return DiscoveryFragment.newInstance()
    }

    override fun createLibraryFragment(): Fragment {
        return LibraryFragment.newInstance()
    }
}