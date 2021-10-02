package com.kanzy.domain.music

import com.kanzy.data.remote.DataState
import com.kanzy.data.remote.apiCall
import com.kanzy.data.remote.model.searchmusic.SearchMusicResponse
import com.kanzy.data.repository.music.MusicRepository
import com.kanzy.domain.base.BaseStateUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMusic @Inject constructor(
    private val repository: MusicRepository
) : BaseStateUseCase<SearchMusic.Param, SearchMusicResponse>() {

    data class Param(
        var keyword: String,
        var pageToken: String? = null,
        var apiKey: String? = null
    )

    override fun execute(params: Param): Flow<DataState<SearchMusicResponse>> {
        return flow {
            apiCall {
                repository.searchMusic(
                    params.keyword,
                    params.pageToken,
                    params.apiKey
                )
            }
        }
    }


}