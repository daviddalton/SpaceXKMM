package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("video_link")
    val videoLink: String?,
    @SerialName("youtube_id")
    val youtubeId: String?,
    @SerialName("article_link")
    val articleLink: String?,
    @SerialName("wikipedia")
    val wikipedia: String?,
    @SerialName("flickr_images")
    val flickrImages: Array<String>?,
    @SerialName("reddit_media")
    val reddit: String?,
)
