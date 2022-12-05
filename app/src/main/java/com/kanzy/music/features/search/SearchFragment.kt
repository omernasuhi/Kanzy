package com.kanzy.music.features.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inomera.sm.ui.foodfilterrestaurants.adapter.SearchAdapter
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.music.R
import com.kanzy.music.base.viewmodel.BaseViewModelFragment
import com.kanzy.music.databinding.FragmentSearchBinding
import com.kanzy.music.extension.hideKeyboard
import com.kanzy.music.extension.observe
import com.kanzy.music.extension.onSubmit
import com.kanzy.music.features.playVideo.PlayVideoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>(),
    SearchAdapter.SearchItemClickListener {

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment().apply {

        }
    }


    lateinit var searchAdapter: SearchAdapter
    override val viewModel: SearchViewModel by viewModels()

    override fun createBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onViewReady(bundle: Bundle?) {
        binding.etSearch.onSearchQueryRightIconChanged()
//        viewModel.getPopularMusics("The Weeknd")
        //serviceTest()
    }

    private fun initAdapter(
        searchResultList: List<SearchMusicDto>,
    ) {
        searchAdapter = SearchAdapter(
            searchResultList, this
        )
        binding.rvMusics.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    override fun onViewListener() {
        super.onViewListener()

        binding.etSearch.onSubmit {
            val textSearch = binding.etSearch.text.toString().trim()
            binding.etSearch.hideKeyboard()
            if (textSearch.isNotEmpty()) {
                viewModel.getPopularMusics(textSearch)
            }
        }

    }

    private  fun serviceTest() {
//        val job = scope.launch {
//            for (i in 0..1000) {
//                    viewModel.getPopularMusics("The weeknd")
//                delay(0)
//            }
//        }

        for (i in 0..1000) {
            viewModel.getPopularMusics("The weeknd")
            Log.e("ATTIM","BOĞDUM")

        }

        // use job.cancel() for cancelling the job or use job.join() for waiting for the job to finish
    }


    override fun onObserveState() {
        super.onObserveState()
        observe(viewModel.searchListLiveData) {
            initAdapter(it)
        }
    }

    override fun musicListItemClicked(item: SearchMusicDto) {
        showBottomDialog(item)
       // item.videoId?.let { viewModel.getPLayMusic(it) }
    }

    private fun showBottomDialog(item: SearchMusicDto) {
        InfoBottomSheet.show(this@SearchFragment, bundle = item )

    }

}