package com.kanzy.data.remote.model.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BasePagedResponse<T>(

    @Json(name = "pageInfo")
    var pageInfo: PageInfo? = null,

    @Json(name = "nextPageToken")
    var nextPageToken: String? = null,

    @Json(name = "prevPageToken")
    var prevPageToken: String? = null,

    @Json(name = "regionCode")
    var regionCode: String? = null

) : BaseResponse<T>()