package com.kanzy.domain.music

import com.kanzy.data.remote.DataState
import com.kanzy.data.remote.apiCall
import com.kanzy.data.repository.music.MusicRepository
import com.kanzy.domain.base.BaseStateUseCase
import com.kanzy.domain.dto.SearchMusicDto
import com.kanzy.domain.dto.toSearchMusicDtoList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMusic @Inject constructor(
    private val repository: MusicRepository
) : BaseStateUseCase<SearchMusic.Param, List<SearchMusicDto>>() {

    data class Param(
        var keyword: String,
        var pageToken: String? = null,
        var apiKey: String? = null
    )

    override fun execute(params: Param): Flow<DataState<List<SearchMusicDto>>> {
        return flow {
            val service = apiCall {
                repository.searchMusic(
                    params.keyword,
                    params.pageToken,
                    params.apiKey
                )
            }

            if (service is DataState.Success) {
                val dtoList = service.response.data?.toSearchMusicDtoList().orEmpty()
                emit(DataState.Success(dtoList))
            }

            if (service is DataState.Error) {
                emit(DataState.Error(service.error))
            }
        }
    }


}