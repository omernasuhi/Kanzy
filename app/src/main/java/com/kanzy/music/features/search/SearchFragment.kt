package com.kanzy.music.features.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.kanzy.music.base.viewmodel.BaseViewModelFragment
import com.kanzy.music.databinding.FragmentSearchBinding
import com.kanzy.music.extension.hideKeyboard
import com.kanzy.music.extension.observe
import com.kanzy.music.extension.onSubmit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment().apply {

        }
    }

    private val adapter by lazy { SearchAdapter() }

    override val viewModel: SearchViewModel by viewModels()

    override fun createBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
        binding.etSearch.onSearchQueryRightIconChanged()
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvMusics.setHasFixedSize(true)
        binding.rvMusics.adapter = adapter
    }

    override fun onViewListener() {
        super.onViewListener()

        binding.etSearch.onSubmit {
            val textSearch = binding.etSearch.text.toString().trim()
            binding.etSearch.hideKeyboard()
            if (textSearch.isNotEmpty()) {
                viewModel.searchMusicList(textSearch)
            }
        }

    }

    override fun onObserveState() {
        super.onObserveState()
        observe(viewModel.searchListLiveData) {
            adapter.items = it
        }
    }

}