package com.kanzy.domain.dto

import android.os.Parcelable
import com.kanzy.data.remote.model.searchmusic.SearchMusic
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchMusicDto(
    var title: String? = null,
    var coverUrl: String? = null,
    var videoId: String? = null,
    var youtubeLink: String? = null
) : Parcelable

fun SearchMusic.toSearchMusicDto() = SearchMusicDto(
    title, coverUrl, videoId, youtubeLink
)

fun List<SearchMusic>.toSearchMusicDtoList() = map { it.toSearchMusicDto() }