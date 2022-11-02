package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch (
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("mission_name")
    val missionName: String,
    @SerialName("launch_date_utc")
    val launchDateUTC: String,
    @SerialName("launch_success")
    val launchSuccess: Boolean?,
    @SerialName("details")
    val details: String?,
    @SerialName("links")
    val links: Links?,
    @SerialName("rocket")
    val rocket: Rocket,
    @SerialName("launch_site")
    val launchSite: LaunchSite,
)