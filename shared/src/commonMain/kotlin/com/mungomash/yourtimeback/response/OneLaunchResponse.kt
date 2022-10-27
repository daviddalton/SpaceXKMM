package com.mungomash.yourtimeback.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OneLaunchResponse(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("mission_name")
    val missionName: String,
)
