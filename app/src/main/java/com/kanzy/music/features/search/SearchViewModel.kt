package com.kanzy.music.features.search

import androidx.lifecycle.MutableLiveData
import com.kanzy.domain.music.GetPopularMusics
import com.kanzy.domain.music.SearchMusic
import com.kanzy.music.R
import com.kanzy.music.base.viewmodel.BaseViewModel
import com.kanzy.music.helper.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMusic: SearchMusic,
    private val resourceManager: ResourceManager,
    private val popularMusics: GetPopularMusics
) : BaseViewModel() {

    val searchListLiveData = MutableLiveData<List<SearchItems>>()
    val searchListErrorLiveData = MutableLiveData<Throwable>()

    fun searchMusicList(keyword: String) = launchOn {
        val param = SearchMusic.Param(keyword)
        executeApi(searchMusic.invoke(param)) {
            val readyList = mutableListOf<SearchItems>()
            readyList.add(SearchItems.Header(resourceManager.getString(R.string.songs)))
            readyList.addAll(it.toSearchItemsContentList())
            searchListLiveData.value = readyList
        }
    }

    fun getPopularMusics() = launchOn {
        executeApi(popularMusics.invoke(Unit)) {
            val readyList = mutableListOf<SearchItems>()
            readyList.add(SearchItems.Header(resourceManager.getString(R.string.popular_songs)))
            readyList.addAll(it.toSearchItemsContentList())
            searchListLiveData.value = readyList
        }
    }


}