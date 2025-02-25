package com.oldgoat5.udemo.network

import io.ktor.client.HttpClient

expect class KtorClientFactory {
    fun create(): HttpClient
}
