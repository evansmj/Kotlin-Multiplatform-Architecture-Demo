package com.oldgoat5.udemo.network

interface KtorLogger : io.ktor.client.plugins.logging.Logger

expect fun getPlatformLogger(): KtorLogger
