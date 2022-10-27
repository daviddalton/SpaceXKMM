package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlickrResponse(
    @SerialName("small")
    val small: String,
    @SerialName("original")
    val original: String,
)
