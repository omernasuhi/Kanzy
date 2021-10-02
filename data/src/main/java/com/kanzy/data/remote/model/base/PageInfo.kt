package com.kanzy.data.remote.model.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageInfo(
    @Json(name = "resultsPerPage")
    var resultsPerPage: Int? = 0,
    @Json(name = "totalResults")
    var totalResults: Int? = 0
)