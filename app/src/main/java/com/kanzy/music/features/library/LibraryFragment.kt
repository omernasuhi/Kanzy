package com.kanzy.music.features.library

import android.os.Bundle
import com.kanzy.music.base.BaseFragment
import com.kanzy.music.databinding.FragmentLibraryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = LibraryFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun createBinding(): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {

    }
}