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

class GetMusicLink @Inject constructor(
    private val repository: MusicRepository
) : BaseStateUseCase<GetMusicLink.Param, List<SearchMusicDto>>() {

    data class Param(
        var videoUrl: String
        )


    override fun execute(params: Param): Flow<DataState<List<SearchMusicDto>>> {
        return flow {
            emit(apiCall { repository.getPopularMusics(params.videoUrl).toSearchMusicDtoList() })
        }

    }

}