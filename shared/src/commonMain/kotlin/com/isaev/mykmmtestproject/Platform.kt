package com.isaev.mykmmtestproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform