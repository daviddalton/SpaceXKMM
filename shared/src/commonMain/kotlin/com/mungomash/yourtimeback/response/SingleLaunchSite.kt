package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleLaunchSite(
    @SerialName("site_id")
    val id: String,
    @SerialName("name")
    val name: String?,
    @SerialName("site_name_long")
    val nameLong: String,
    @SerialName("status")
    val status: String?,
    @SerialName("attempted_launches")
    val attemptedLaunches: Int,
    @SerialName("successful_launches")
    val successfulLaunches: Int,
    @SerialName("wikipedia")
    val wikiLink: String?,
    @SerialName("details")
    val details: String?,
    @SerialName("vehicles_launched")
    val vehiclesLaunched: Array<String>,
    @SerialName("location")
    val location: LaunchSiteLocation,
)
