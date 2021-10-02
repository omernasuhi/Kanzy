package com.kanzy.data.remote.model.playlistmusic

import com.kanzy.data.remote.model.base.BasePagedResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PlayListMusicResponse : BasePagedResponse<List<PlayListMusic>>()