package com.kanzy.data.remote.model.searchmusic

import com.kanzy.data.remote.model.base.BasePagedResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SearchMusicResponse : BasePagedResponse<List<SearchMusic>>()