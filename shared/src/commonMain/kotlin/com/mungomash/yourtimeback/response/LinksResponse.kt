package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksResponse(
    @SerialName("webcast")
    val webcast: String?,
    @SerialName("youtube_id")
    val youtubeId: String?,
    @SerialName("article")
    val article: String?,
    @SerialName("wikipedia")
    val wikipedia: String?,
)
