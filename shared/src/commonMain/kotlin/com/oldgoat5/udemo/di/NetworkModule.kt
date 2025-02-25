package com.oldgoat5.udemo.di

import com.oldgoat5.udemo.config.Config
import com.oldgoat5.udemo.network.stats.IStatsRemoteDataSource
import com.oldgoat5.udemo.network.stats.StatsRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "pro-api.coinmarketcap.com"
                }
                header("X-CMC_PRO_API_KEY", Config.CMC_API_KEY)
            }
        }
    }

    single<IStatsRemoteDataSource> { StatsRemoteDataSource(get())}
}
