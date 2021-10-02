package com.kanzy.music.features.home

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.kanzy.music.base.BaseFragment
import com.kanzy.music.databinding.FragmentHomeBinding
import com.kanzy.music.features.home.adapter.HomeAdapterFragmentFactory
import com.kanzy.music.features.home.adapter.HomePage.Companion.toHomePageFromMenuItemId
import com.kanzy.music.features.home.adapter.HomePage.Companion.toHomePageMenuItemIdFromPosition
import com.kanzy.music.features.home.adapter.HomeViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var adapterFragmentFactory: HomeAdapterFragmentFactory

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {

        }
    }

    override fun createBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
        val vpAdapter = HomeViewPagerAdapter(this, adapterFragmentFactory)
        binding.vpMain.adapter = vpAdapter
        binding.vpMain.offscreenPageLimit = vpAdapter.itemCount
        binding.vpMain.isUserInputEnabled = false


        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val itemId = position.toHomePageMenuItemIdFromPosition()
                if (binding.bottomNavView.selectedItemId != itemId)
                    binding.bottomNavView.selectedItemId = itemId
            }
        })

        // set bottom nav
        binding.bottomNavView.setOnItemSelectedListener { item ->
            val position = item.toPagePosition()
            if (binding.vpMain.currentItem != position) {
                setItem(position)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun MenuItem.toPagePosition(): Int {
        return itemId.toHomePageFromMenuItemId().position
    }

    private fun setItem(position: Int) {
        binding.vpMain.setCurrentItem(position, false)
    }

    override fun onDestroyView() {
        /*
            Without setting ViewPager2 Adapter it causes memory leak
            https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig
         */
        binding.vpMain.adapter = null
        super.onDestroyView()
    }
}