package com.oldgoat5.udemo.network.stats

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class StatsRemoteDataSource(private val httpClient: HttpClient) : IStatsRemoteDataSource {

    override suspend fun fetchStats(): StatsResponse {
        return httpClient.get(urlString = "/v2/cryptocurrency/quotes/latest").body()
    }
}
