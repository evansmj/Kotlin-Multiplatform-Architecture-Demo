package com.oldgoat5.udemo.network.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    val data: Map<String, StatsData>
)

@Serializable
data class StatsData(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val isActive: Int,
    val isFiat: Int,
    val circulatingSupply: Double,
    val totalSupply: Double,
    val maxSupply: Double?,
    val dateAdded: String,
    val numMarketPairs: Int,
    val cmcRank: Int,
    val lastUpdated: String,
    val tags: List<String>,
    val platform: String?,
    val selfReportedCirculatingSupply: Double?,
    val selfReportedMarketCap: Double?,
    val quote: Quote
)

@Serializable
data class Quote(
    @SerialName("USD") val usd: USDQuote
)

@Serializable
data class USDQuote(
    val price: Double,
    @SerialName("volume_24h") val volume24h: Double,
    @SerialName("volume_change_24h") val volumeChange24h: Double,
    @SerialName("percent_change_1h") val percentChange1h: Double,
    @SerialName("percent_change_24h") val percentChange24h: Double,
    @SerialName("percent_change_7d") val percentChange7d: Double,
    @SerialName("percent_change_30d") val percentChange30d: Double,
    @SerialName("market_cap") val marketCap: Double,
    @SerialName("market_cap_dominance") val marketCapDominance: Double,
    @SerialName("fully_diluted_market_cap") val fullyDilutedMarketCap: Double,
    @SerialName("last_updated") val lastUpdated: String
)
