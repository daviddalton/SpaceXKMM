package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch (
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?,
    @SerialName("details")
    val details: String?,
    @SerialName("links")
    val links: LinksResponse?,
    @SerialName("rocket")
    val rocketId: String,
    @SerialName("upcoming")
    val upcomingLaunch: Boolean,
    @SerialName("launchpad")
    val launchpad: String,

    )