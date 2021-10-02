package com.kanzy.music.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kanzy.music.features.home.adapter.HomePage.Companion.toHomePageFromPosition

class HomeViewPagerAdapter(
    fragment: Fragment,
    private val fragmentFactory: HomeAdapterFragmentFactory
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return HomePage.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position.toHomePageFromPosition()) {
            HomePage.SEARCH -> fragmentFactory.createSearchFragment()
            HomePage.DISCOVERY -> fragmentFactory.createDiscoveryFragment()
            HomePage.LIBRARY -> fragmentFactory.createLibraryFragment()
        }
    }
}