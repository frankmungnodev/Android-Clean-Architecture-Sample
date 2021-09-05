package com.selftaughtdev.data.network.dto.response.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(

    @Json(name = "status_code")
    val code: Int,

    @Json(name = "status_message")
    val message: String,

    @Json(name = "success")
    val success: Boolean
)