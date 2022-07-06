package com.kanzy.music.features.search

import androidx.lifecycle.MutableLiveData
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.domain.music.GetPLayMusic
import com.kanzy.domain.music.GetPopularMusics
import com.kanzy.music.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val popularMusics: GetPopularMusics,
    private val getPLayMusic: GetPLayMusic
) : BaseViewModel() {

    var searchListLiveData = MutableLiveData<List<SearchMusicDto>>()
    var playMusicLiveData = MutableLiveData<String>()

    fun getPopularMusics(keyword: String) = launchOn {
        val param = GetPopularMusics.Param(keyword)
        executeApi(popularMusics.invoke(param)) {
           searchListLiveData.value = it
        }
    }

    fun getPLayMusic(videoId: String) = launchOn {
        val param = GetPLayMusic.Param(videoId)
        executeApi(getPLayMusic.invoke(param)) {
            playMusicLiveData.value = it
        }
    }



}