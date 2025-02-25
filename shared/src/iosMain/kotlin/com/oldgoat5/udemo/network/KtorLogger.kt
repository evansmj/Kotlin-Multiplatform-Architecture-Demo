package com.oldgoat5.udemo.network

import platform.Foundation.NSLog

object IOSKtorLogger : KtorLogger {
    override fun log(message: String) {
        NSLog(message)
    }
}

actual fun getPlatformLogger(): KtorLogger = IOSKtorLogger
