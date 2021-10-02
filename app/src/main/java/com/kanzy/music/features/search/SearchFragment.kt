package com.kanzy.music.features.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.kanzy.music.base.viewmodel.BaseViewModelFragment
import com.kanzy.music.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>() {

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment().apply {

        }
    }

    override val viewModel: SearchViewModel by viewModels()

    override fun createBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {

    }


}