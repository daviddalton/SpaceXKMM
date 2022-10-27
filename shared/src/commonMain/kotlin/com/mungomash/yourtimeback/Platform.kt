package com.mungomash.yourtimeback

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform