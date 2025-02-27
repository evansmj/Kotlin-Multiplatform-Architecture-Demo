package com.oldgoat5.udemo.network.stats

object BitcoinStatsTestDataFactory {
    fun createBitcoinStatsResponse(
        data: Map<String, BitcoinStatsData> = mapOf("bitcoin" to createBitcoinStatsData())
    ): BitcoinStatsResponse {
        return BitcoinStatsResponse(data)
    }

    fun createBitcoinStatsData(
        id: Int = 1,
        name: String = "Bitcoin",
        symbol: String = "BTC",
        slug: String = "bitcoin",
        isActive: Int = 1,
        isFiat: Int = 0,
        circulatingSupply: Double = 19000000.0,
        totalSupply: Double = 21000000.0,
        maxSupply: Double? = 21000000.0,
        dateAdded: String = "2013-04-28T00:00:00.000Z",
        numMarketPairs: Int = 10000,
        cmcRank: Int = 1,
        lastUpdated: String = "2024-02-27T12:00:00.000Z",
        tags: List<Tag> = listOf(createTag()),
        platform: String? = null,
        selfReportedCirculatingSupply: Double? = null,
        selfReportedMarketCap: Double? = null,
        quote: Quote = createQuote(),
        infiniteSupply: Boolean = false,
        tvlRatio: Double? = null
    ): BitcoinStatsData {
        return BitcoinStatsData(
            id, name, symbol, slug, isActive, isFiat, circulatingSupply, totalSupply, maxSupply,
            dateAdded, numMarketPairs, cmcRank, lastUpdated, tags, platform,
            selfReportedCirculatingSupply, selfReportedMarketCap, quote, infiniteSupply, tvlRatio
        )
    }

    fun createQuote(
        usd: USDQuote = createUSDQuote()
    ): Quote {
        return Quote(usd)
    }

    fun createUSDQuote(
        price: Double = 50000.0,
        volume24h: Double = 10000000000.0,
        volumeChange24h: Double = 5.0,
        percentChange1h: Double = 0.1,
        percentChange24h: Double = 2.5,
        percentChange7d: Double = 10.0,
        percentChange30d: Double = 15.0,
        percentChange60d: Double = 25.0,
        percentChange90d: Double = 40.0,
        marketCap: Double = 900000000000.0,
        marketCapDominance: Double = 50.0,
        fullyDilutedMarketCap: Double = 1050000000000.0,
        tvl: Double? = null,
        lastUpdated: String = "2024-02-27T12:00:00.000Z"
    ): USDQuote {
        return USDQuote(
            price, volume24h, volumeChange24h, percentChange1h, percentChange24h,
            percentChange7d, percentChange30d, percentChange60d, percentChange90d,
            marketCap, marketCapDominance, fullyDilutedMarketCap, tvl, lastUpdated
        )
    }

    fun createTag(
        slug: String = "store-of-value",
        name: String = "Store of Value",
        category: String = "asset"
    ): Tag {
        return Tag(slug, name, category)
    }
}
