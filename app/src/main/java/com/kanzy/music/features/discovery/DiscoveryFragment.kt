package com.kanzy.music.features.discovery

import android.os.Bundle
import com.kanzy.music.base.BaseFragment
import com.kanzy.music.databinding.FragmentDiscoveryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoveryFragment : BaseFragment<FragmentDiscoveryBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = DiscoveryFragment().apply {

        }
    }

    override fun createBinding(): FragmentDiscoveryBinding {
        return FragmentDiscoveryBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {

    }


}