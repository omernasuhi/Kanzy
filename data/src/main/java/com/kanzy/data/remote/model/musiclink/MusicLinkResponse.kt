package com.kanzy.data.remote.model.musiclink

import com.kanzy.data.remote.model.base.BaseResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MusicLinkResponse : BaseResponse<MusicLink>()