package com.mungomash.yourtimeback

import com.mungomash.yourtimeback.response.OneLaunchResponse
import com.mungomash.yourtimeback.response.RocketLaunch
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
    suspend fun getRocketLaunches(): List<RocketLaunch> {
        return httpClient.get("https://api.spacexdata.com/v4/launches").body()
    }

    @Throws(Exception::class)
    suspend fun getSingleRocketLaunch(flightNumber: Int): OneLaunchResponse {
        return httpClient.get("https://api.spacexdata.com/v3/launches/${flightNumber}").body()
    }
}