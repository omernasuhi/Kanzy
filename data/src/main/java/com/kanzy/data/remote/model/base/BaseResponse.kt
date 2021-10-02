package com.kanzy.data.remote.model.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse<T>(

    @Json(name = "errors")
    var errors: List<String> = emptyList(),

    @Json(name = "message")
    var message: String? = null,

    @Json(name = "succeeded")
    var succeeded: Boolean = false,

    @Json(name = "data")
    var data: T? = null

)