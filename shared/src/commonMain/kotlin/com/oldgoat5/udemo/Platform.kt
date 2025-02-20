package com.oldgoat5.udemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform