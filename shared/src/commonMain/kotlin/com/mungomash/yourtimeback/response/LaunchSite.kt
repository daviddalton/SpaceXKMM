package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchSite(
    @SerialName("site_id")
    val id: String,
    @SerialName("site_name")
    val name: String?,
    @SerialName("site_name_long")
    val nameLong: String,
)