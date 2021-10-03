package com.kanzy.data.remote.model.searchmusic

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchMusic(
    @Json(name = "coverUrl")
    var coverUrl: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "videoId")
    var videoId: String? = null,
    @Json(name = "youtubeLink")
    var youtubeLink: String? = null
)