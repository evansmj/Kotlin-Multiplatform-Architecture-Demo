package com.oldgoat5.udemo.network.stats

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class BitcoinStatsRemoteDataSource(private val httpClient: HttpClient) : IBitcoinStatsRemoteDataSource {

    @NativeCoroutines
    override suspend fun fetchStats(): BitcoinStatsResponse {
        return httpClient.get {
            url {
                path("/v2/cryptocurrency/quotes/latest")
                parameters.append("id", "1")
            }
        }.body()
    }
}
