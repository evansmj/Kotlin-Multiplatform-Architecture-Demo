package com.oldgoat5.udemo.network.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    @SerialName("data") val data: Map<String, StatsData>
)

@Serializable
data class StatsData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("slug") val slug: String,
    @SerialName("is_active") val isActive: Int,
    @SerialName("is_fiat") val isFiat: Int,
    @SerialName("circulating_supply") val circulatingSupply: Double,
    @SerialName("total_supply") val totalSupply: Double,
    @SerialName("max_supply") val maxSupply: Double?,
    @SerialName("date_added") val dateAdded: String,
    @SerialName("num_market_pairs") val numMarketPairs: Int,
    @SerialName("cmc_rank") val cmcRank: Int,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("tags") val tags: List<Tag>,
    @SerialName("platform") val platform: String?,
    @SerialName("self_reported_circulating_supply") val selfReportedCirculatingSupply: Double?,
    @SerialName("self_reported_market_cap") val selfReportedMarketCap: Double?,
    @SerialName("quote") val quote: Quote,
    @SerialName("infinite_supply") val infiniteSupply: Boolean,
    @SerialName("tvl_ratio") val tvlRatio: Double? = null
)

@Serializable
data class Quote(
    @SerialName("USD") val usd: USDQuote
)

@Serializable
data class USDQuote(
    @SerialName("price") val price: Double,
    @SerialName("volume_24h") val volume24h: Double,
    @SerialName("volume_change_24h") val volumeChange24h: Double,
    @SerialName("percent_change_1h") val percentChange1h: Double,
    @SerialName("percent_change_24h") val percentChange24h: Double,
    @SerialName("percent_change_7d") val percentChange7d: Double,
    @SerialName("percent_change_30d") val percentChange30d: Double,
    @SerialName("percent_change_60d") val percentChange60d: Double,
    @SerialName("percent_change_90d") val percentChange90d: Double,
    @SerialName("market_cap") val marketCap: Double,
    @SerialName("market_cap_dominance") val marketCapDominance: Double,
    @SerialName("fully_diluted_market_cap") val fullyDilutedMarketCap: Double,
    @SerialName("tvl") val tvl: Double?,
    @SerialName("last_updated") val lastUpdated: String
)

@Serializable
data class Tag(
    @SerialName("slug") val slug: String,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String
)
