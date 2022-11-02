package com.mungomash.yourtimeback

import com.mungomash.yourtimeback.response.LaunchSite
import com.mungomash.yourtimeback.response.Rocket
import com.mungomash.yourtimeback.response.RocketLaunch
import com.mungomash.yourtimeback.response.SingleLaunchSite
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class SpaceX {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    suspend fun getRocketLaunches(order: String): List<RocketLaunch> {
        return httpClient.get("https://api.spacexdata.com/v3/launches?order=${order}").body()
    }

    @Throws(Exception::class)
    suspend fun getSingleRocketLaunch(flightNumber: Int): RocketLaunch {
        return httpClient.get("https://api.spacexdata.com/v3/launches/${flightNumber}").body()
    }

    @Throws(Exception::class)
    suspend fun getLaunchSite(launchSiteId: String): SingleLaunchSite {
        return httpClient.get("https://api.spacexdata.com/v3/launchpads/${launchSiteId}").body()
    }

    @Throws(Exception::class)
    suspend fun getSingleRocket(rocketId: String): Rocket {
        return httpClient.get("https://api.spacexdata.com/v3/rockets/${rocketId}").body()
    }
}