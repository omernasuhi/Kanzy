package com.kanzy.data.remote.model.musiclink

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusicLink(

    @Json(name = "audioLink")
    var audioLink: String? = null,

    @Json(name = "videoId")
    var videoId: String? = null,

    @Json(name = "videoLink")
    var videoLink: String? = null,

    @Json(name = "youtubeLink")
    var youtubeLink: String? = null

)