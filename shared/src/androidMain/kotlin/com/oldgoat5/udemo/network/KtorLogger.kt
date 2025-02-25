package com.oldgoat5.udemo.network

import android.util.Log

object AndroidKtorLogger : KtorLogger {
    override fun log(message: String) {
        Log.d("KtorLogger", message)
    }
}

actual fun getPlatformLogger(): KtorLogger = AndroidKtorLogger
